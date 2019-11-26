package com.payment.card.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class PaymentTypeRegistrationResponse {

	private String id;
	private PaymentType paymentType;
	private int count;

}
