package com.payment.concentrator.merchant;

import com.payment.commons.PaymentType;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Merchant {

	private String id;
	private String name;
	private String password;
	private List<PaymentType> paymentTypes;

	public Merchant(String id, String name, String password, List<PaymentType> paymentTypes) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.paymentTypes = paymentTypes;
	}
}
