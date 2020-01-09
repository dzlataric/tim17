package com.payment.paypal.payment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponse {

	private String id;
	private String state;
	private List<PaymentLink> links;

}
