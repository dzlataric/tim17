package com.payment.paypal.subscription;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BillingPlan {

	private Long id;
	private String billingPlanId;
	private Long sellerId;
	private BillingPlanStatus status;

}
