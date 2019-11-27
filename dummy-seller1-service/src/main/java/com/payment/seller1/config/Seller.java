package com.payment.seller1.config;

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

}
