package com.payment.paypal.subscription;

public interface SubscriptionService {

	Subscription createSubscription(SubscriptionRequest subscriptionRequest);

	String executeSubscription(ExecuteSubscription executeSubscription);

}
