package com.payment.concentrator.payment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentTypeRegistrationResponse {

	private String id;
	private PaymentType paymentType;
	private int count;

}
