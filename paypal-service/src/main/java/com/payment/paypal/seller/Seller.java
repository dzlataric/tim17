package com.payment.paypal.seller;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Seller {

	private Long id;
	private String email;
	private String merchantId;
	private Boolean allowsSubscription;

}
