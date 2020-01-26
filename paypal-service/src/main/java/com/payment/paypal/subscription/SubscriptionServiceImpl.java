package com.payment.paypal.subscription;

import com.paypal.api.payments.ChargeModels;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.Plan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	@Override
	public String createSubscription() {
		// Build Plan object
		Plan plan = new Plan();
		plan.setName("T-Shirt of the Month Club Plan");
		plan.setDescription("Template creation.");
		plan.setType("fixed");

		// Payment_definitions
		PaymentDefinition paymentDefinition = new PaymentDefinition();
		paymentDefinition.setName("Regular Payments");
		paymentDefinition.setType("REGULAR");
		paymentDefinition.setFrequency("MONTH");
		paymentDefinition.setFrequencyInterval("1");
		paymentDefinition.setCycles("12");

		// Currency
		Currency currency = new Currency();
		currency.setCurrency("USD");
		currency.setValue("20");
		paymentDefinition.setAmount(currency);

		// Charge_models
		ChargeModels chargeModels = new com.paypal.api.payments.ChargeModels();
		chargeModels.setType("SHIPPING");
		chargeModels.setAmount(currency);
		List<ChargeModels> chargeModelsList = new ArrayList<>();
		chargeModelsList.add(chargeModels);
		paymentDefinition.setChargeModels(chargeModelsList);

		// Payment_definition
		List<PaymentDefinition> paymentDefinitionList = new ArrayList<>();
		paymentDefinitionList.add(paymentDefinition);
		plan.setPaymentDefinitions(paymentDefinitionList);

		// Merchant_preferences
		MerchantPreferences merchantPreferences = new MerchantPreferences();
		merchantPreferences.setSetupFee(currency);
		merchantPreferences.setCancelUrl("https://example.com/cancel");
		merchantPreferences.setReturnUrl("https://example.com/return");
		merchantPreferences.setMaxFailAttempts("0");
		merchantPreferences.setAutoBillAmount("YES");
		merchantPreferences.setInitialFailAmountAction("CONTINUE");
		plan.setMerchantPreferences(merchantPreferences);
		return null;
	}
}
