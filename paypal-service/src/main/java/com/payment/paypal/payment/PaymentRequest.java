package com.payment.paypal.payment;

import com.payment.commons.Currency;
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
	private String successUrl;
	private String failedUrl;

}
