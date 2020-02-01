package com.payment.paypal.seller;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationRequest {

	private String merchantId;
	private String email;
	private Boolean allowsSubscription;

}
