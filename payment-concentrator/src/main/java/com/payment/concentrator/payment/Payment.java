package com.payment.concentrator.payment;

import com.payment.commons.PaymentType;

import lombok.Builder;
import lombok.Value;


public class Payment {

	private String id;
	private PaymentType paymentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
