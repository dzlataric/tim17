package com.payment.paypal.subscription;

import com.payment.commons.Currency;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillingPlanRequest {

	private String sellerEmail;
	private String name;
	private String description;
	private BillingPlanType billingPlanType;
	private PaymentType paymentType;
	private Frequency frequency;
	private int frequencyInterval;
	private int cycles;
	private Currency currency;
	private Double amount;
	private String successUrl;
	private String failedUrl;

}
