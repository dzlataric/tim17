package com.payment.paypal.subscription;

import com.payment.paypal.seller.SellerService;
import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.Plan;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
class BillingPlanServiceImpl implements BillingPlanService {

	private final APIContext paypalApiContext;
	private final BillingPlanRepository billingPlanRepository;
	private final SellerService sellerService;

	@Override
	public BillingPlan createAndActivateBillingPlan(final BillingPlanRequest request) {
		Plan plan = preparePlan(request);
		PaymentDefinition paymentDefinition = preparePaymentDefinition(request);
		Currency currency = prepareCurrency(request, paymentDefinition);
		setChargeModels(paymentDefinition, currency);
		plan.setPaymentDefinitions(List.of(paymentDefinition));
		plan.setMerchantPreferences(prepareMerchantPreferences(request, currency));
		try {
			final var createdPlan = plan.create(paypalApiContext);
			return activatePlan(createdPlan, saveBillingPlan(createdPlan, request.getSellerEmail()));
		} catch (PayPalRESTException e) {
			log.error("Billing plan creation exception", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public BillingPlanEntity getBillingPlanBySellersEmail(final String email) {
		return billingPlanRepository.findBySellerId(sellerService.getIdByEmail(email)).orElseThrow();
	}

	private BillingPlan activatePlan(final Plan createdPlan, final BillingPlanEntity billingPlanEntity) throws PayPalRESTException {
		patchBillingPlan(createdPlan);
		billingPlanEntity.setStatus(BillingPlanStatus.ACTIVE);
		log.info("New plan state: {}", billingPlanEntity.getStatus());
		return BillingPlan.builder()
			.id(billingPlanEntity.getId())
			.billingPlanId(billingPlanEntity.getBillingPlanId())
			.sellerId(billingPlanEntity.getSeller().getId())
			.status(billingPlanEntity.getStatus())
			.build();
	}

	private void patchBillingPlan(final Plan createdPlan) throws PayPalRESTException {
		List<Patch> patchRequestList = new ArrayList<>();
		Patch patch = new Patch();
		patch.setPath("/");
		patch.setValue(Map.of("state", BillingPlanStatus.ACTIVE.name()));
		patch.setOp("replace");
		patchRequestList.add(patch);
		createdPlan.update(paypalApiContext, patchRequestList);
	}

	private BillingPlanEntity saveBillingPlan(final Plan plan, final String sellerEmail) {
		final var paymentDefinition = plan.getPaymentDefinitions().stream().findFirst();
		if (paymentDefinition.isEmpty()) {
			throw new IllegalArgumentException(
				String.format("Payment definition not created for plan with id %s", plan.getId()));
		}
		return billingPlanRepository.save(BillingPlanEntity.builder()
			.billingPlanId(plan.getId())
			.name(plan.getName())
			.description(plan.getDescription())
			.amount(Double.valueOf(paymentDefinition.get().getAmount().getValue()))
			.type(BillingPlanType.valueOf(plan.getType()))
			.frequency(Frequency.valueOf(paymentDefinition.get().getFrequency()))
			.status(BillingPlanStatus.valueOf(plan.getState()))
			.seller(sellerService.getByEmail(sellerEmail))
			.build());

	}

	private MerchantPreferences prepareMerchantPreferences(final BillingPlanRequest request, final Currency currency) {
		MerchantPreferences merchantPreferences = new MerchantPreferences();
		merchantPreferences.setSetupFee(currency);
		merchantPreferences.setCancelUrl(request.getFailedUrl());
		merchantPreferences.setReturnUrl(request.getSuccessUrl());
		merchantPreferences.setMaxFailAttempts("0");
		//Allow auto billing for the outstanding amount of the agreement in the next cycle. Allowed values: `YES`, `NO`. Default is `NO`.
		merchantPreferences.setAutoBillAmount("YES");
		//Action to take if a failure occurs during initial payment. Allowed values: `CONTINUE`, `CANCEL`. Default is continue.
		merchantPreferences.setInitialFailAmountAction(FailAction.CANCEL.name());
		return merchantPreferences;
	}

	private void setChargeModels(final PaymentDefinition paymentDefinition, final Currency currency) {
		ChargeModels chargeModels = new ChargeModels();
		chargeModels.setType(ChargeModelType.SHIPPING.name()); //Allowed values: `SHIPPING`, `TAX`
		chargeModels.setAmount(currency);
		paymentDefinition.setChargeModels(List.of(chargeModels));
	}

	private Currency prepareCurrency(final BillingPlanRequest request, final PaymentDefinition paymentDefinition) {
		Currency currency = new Currency();
		currency.setCurrency(request.getCurrency().name());
		currency.setValue(String.format("%.2f", request.getAmount()));
		paymentDefinition.setAmount(currency);
		return currency;
	}

	private PaymentDefinition preparePaymentDefinition(final BillingPlanRequest request) {
		PaymentDefinition paymentDefinition = new PaymentDefinition();
		paymentDefinition.setName("Regular Payments");
		paymentDefinition.setType(request.getPaymentType().name()); //Allowed values: `TRIAL`, `REGULAR`
		paymentDefinition.setFrequency(request.getFrequency().name()); //Allowed values: `WEEK`, `DAY`, `YEAR`, `MONTH`
		paymentDefinition.setFrequencyInterval(String.valueOf(request.getFrequencyInterval()));
		paymentDefinition.setCycles(String.valueOf(request.getCycles()));
		return paymentDefinition;
	}

	private Plan preparePlan(final BillingPlanRequest request) {
		Plan plan = new Plan();
		plan.setName(request.getName());
		plan.setDescription(request.getDescription());
		plan.setType(request.getBillingPlanType().name()); //Allowed values: FIXED, INFINITE
		return plan;
	}
}
