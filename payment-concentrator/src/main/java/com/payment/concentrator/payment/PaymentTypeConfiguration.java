package com.payment.concentrator.payment;

import java.util.Map;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentTypeConfiguration {

	private Map<String, PaymentType> paymentTypes;

}
