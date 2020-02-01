package com.payment.paypal.subscription;

import com.payment.paypal.payment.PaymentMethod;
import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	private final APIContext paypalApiContext;
	private final BillingPlanService billingPlanService;
	private final SubscriptionRepository subscriptionRepository;

	@Override
	public String executeSubscription(final ExecuteSubscription executeSubscription) {
		try {
			return Agreement.execute(paypalApiContext, executeSubscription.getToken()).toJSON();
		} catch (PayPalRESTException e) {
			log.error("Execute subscription exception", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Subscription createSubscription(final SubscriptionRequest subscriptionRequest) {
		final var billingPlan = billingPlanService.getBillingPlanBySellersEmail(subscriptionRequest.getSellerEmail());
		if (!billingPlan.getSeller().getAllowsSubscription()) {
			throw new RuntimeException("This seller does not allow subscriptions!");
		}
		var agreement = createAgreement(subscriptionRequest);
		setAgreementPlan(subscriptionRequest, agreement, billingPlan);
		Payer payer = new Payer();
		payer.setPaymentMethod(PaymentMethod.PAYPAL.name());
		agreement.setPayer(payer);
		setShippingAddress(agreement);

		try {
			return saveSubscription(subscriptionRequest, agreement, billingPlan);
		} catch (UnsupportedEncodingException e) {
			log.error("Encoding exception", e);
			throw new RuntimeException(e.getMessage());
		} catch (PayPalRESTException e) {
			log.error("Paypal exception", e);
			throw new RuntimeException(e.getMessage());
		} catch (MalformedURLException e) {
			log.error("MalformedURL exception", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	private Subscription saveSubscription(final SubscriptionRequest subscriptionRequest, Agreement agreement, final BillingPlanEntity billingPlan)
		throws PayPalRESTException, MalformedURLException, UnsupportedEncodingException {
		agreement = agreement.create(paypalApiContext);
		final var approvalUrl = getApprovalUrl(agreement);
		log.info("Token received: {}", agreement.getToken());
		final var subscriptionEntity = subscriptionRepository.save(SubscriptionEntity.builder()
			.billingPlan(billingPlan)
			.payer(subscriptionRequest.getUser())
			.name(agreement.getName())
			.description(agreement.getDescription())
			.token(agreement.getToken())
			.approvalUrl(approvalUrl)
			.build());
		return Subscription.builder()
			.id(subscriptionEntity.getId())
			.user(subscriptionEntity.getPayer())
			.name(subscriptionEntity.getName())
			.description(subscriptionEntity.getDescription())
			.token(subscriptionEntity.getToken())
			.billingPlanId(billingPlan.getBillingPlanId())
			.approvalUrl(approvalUrl)
			.build();
	}

	private String getApprovalUrl(final Agreement agreement) {
		return agreement.getLinks().stream()
			.filter(l -> "approval_url".equals(l.getRel()))
			.findFirst()
			.orElseThrow()
			.getHref();
	}

	private void setShippingAddress(final Agreement agreement) {
		ShippingAddress shipping = new ShippingAddress();
		shipping.setLine1("111 First Street");
		shipping.setCity("Saratoga");
		shipping.setState("CA");
		shipping.setPostalCode("95070");
		shipping.setCountryCode("US");
		agreement.setShippingAddress(shipping);
	}

	private void setAgreementPlan(final SubscriptionRequest subscriptionRequest, final Agreement agreement,
		final BillingPlanEntity billingPlan) {
		Plan plan = new Plan();
		plan.setId(billingPlan.getBillingPlanId());
		agreement.setPlan(plan);
	}

	private Agreement createAgreement(final SubscriptionRequest subscriptionRequest) {
		Agreement agreement = new Agreement();
		agreement.setName("Agreement" + subscriptionRequest.getUser());
		agreement.setDescription("Basic Agreement");
		agreement.setStartDate(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toString());
		return agreement;
	}

}
