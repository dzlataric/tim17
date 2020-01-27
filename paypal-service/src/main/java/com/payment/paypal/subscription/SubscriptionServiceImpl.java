package com.payment.paypal.subscription;

import com.payment.paypal.payment.PaymentMethod;
import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.Plan;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	private final APIContext paypalApiContext;

	@Override
	public String createBillingPlan(final BillingPlanRequest request) {
		Plan plan = preparePlan(request);
		PaymentDefinition paymentDefinition = preparePaymentDefinition(request);
		Currency currency = prepareCurrency(request, paymentDefinition);
		setChargeModels(paymentDefinition, currency);
		setPaymentDefinitions(plan, paymentDefinition);
		plan.setMerchantPreferences(prepareMerchantPreferences(request, currency));
		try {
			final var createdPlan = plan.create(paypalApiContext);
			activatePlan(createdPlan);
			log.info("New plan state: {}", createdPlan.getState());
			return createdPlan.toJSON();
		} catch (PayPalRESTException e) {
			log.error("Billing plan creation exception", e);
			throw new RuntimeException(e.getMessage());
		}
	}

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
	public String createSubscription(final SubscriptionRequest subscriptionRequest) {
		// Create new agreement
		Agreement agreement = new Agreement();
		agreement.setName("Base Agreement");
		agreement.setDescription("Basic Agreement");
		log.info("2019-01-27T9:45:04Z compared to {}", ZonedDateTime.now().plusDays(1).toString());
		agreement.setStartDate("2020-01-28T9:45:04Z");

		Plan plan = new Plan();
		plan.setId(subscriptionRequest.getBillingPlanId());
		agreement.setPlan(plan);

		Payer payer = new Payer();
		payer.setPaymentMethod(PaymentMethod.PAYPAL.name());
		agreement.setPayer(payer);

		ShippingAddress shipping = new ShippingAddress();
		shipping.setLine1("111 First Street");
		shipping.setCity("Saratoga");
		shipping.setState("CA");
		shipping.setPostalCode("95070");
		shipping.setCountryCode("US");
		agreement.setShippingAddress(shipping);

		try {
			agreement = agreement.create(paypalApiContext);
			log.info("Token received: {}", agreement.getToken());
			return agreement.toJSON();
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

	private void activatePlan(final Plan createdPlan) throws PayPalRESTException {
		List<Patch> patchRequestList = new ArrayList<>();
		Patch patch = new Patch();
		patch.setPath("/");
		patch.setValue(Map.of("state", "ACTIVE"));
		patch.setOp("replace");
		patchRequestList.add(patch);
		createdPlan.update(paypalApiContext, patchRequestList);
	}

	private void setPaymentDefinitions(final Plan plan, final PaymentDefinition paymentDefinition) {
		List<PaymentDefinition> paymentDefinitionList = new ArrayList<>();
		paymentDefinitionList.add(paymentDefinition);
		plan.setPaymentDefinitions(paymentDefinitionList);
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
		List<ChargeModels> chargeModelsList = new ArrayList<>();
		chargeModelsList.add(chargeModels);
		paymentDefinition.setChargeModels(chargeModelsList);
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
		plan.setType(request.getSubscriptionType().name()); //Allowed values: FIXED, INFINITE
		return plan;
	}
}
