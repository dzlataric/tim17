package com.payment.concentrator.payment;

import com.payment.commons.PaymentType;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Payment {

	private String id;
	private PaymentType paymentType;

}
