package com.payment.concentrator.seller;

import com.payment.commons.PaymentType;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Seller {

	private String id;
	private String name;
	private List<PaymentType> paymentTypes;

	public Seller(String id, String name, List<PaymentType> paymentTypes) {
		this.id = id;
		this.name = name;
		this.paymentTypes = paymentTypes;
	}
}
