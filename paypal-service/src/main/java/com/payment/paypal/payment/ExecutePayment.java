package com.payment.paypal.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExecutePayment {

	private String payerId;
	private String paymentId;
	private String token;

}
