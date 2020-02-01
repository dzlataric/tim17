package com.payment.paypal.subscription;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Subscription {

	private Long id;
	private String user;
	private String name;
	private String description;
	private String token;
	private String billingPlanId;
	private String approvalUrl;

}
