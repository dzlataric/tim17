package com.payment.paypal.subscription;

public interface SubscriptionService {

	String createBillingPlan(BillingPlanRequest billingPlanRequest);

	String createSubscription(SubscriptionRequest subscriptionRequest);

	String executeSubscription(ExecuteSubscription executeSubscription);

}
