package com.payment.paypal.subscription;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriptionRequest {

	private String sellerEmail;
	private String user;

}
