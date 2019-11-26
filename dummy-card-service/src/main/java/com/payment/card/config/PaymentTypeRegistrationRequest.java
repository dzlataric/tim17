package com.payment.card.config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class PaymentTypeRegistrationRequest {

	private String id;
	private PaymentType paymentType;

}
