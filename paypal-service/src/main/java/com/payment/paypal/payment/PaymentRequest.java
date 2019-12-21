package com.payment.paypal.payment;

import com.payment.paypal.payment.Currency;
import com.payment.paypal.payment.Intent;
import com.payment.paypal.payment.PaymentMethod;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {

	private String id;
	private Double amount;
	private Currency currency;
	private String description;
	private Intent intent;
	private PaymentMethod paymentMethod;

}
