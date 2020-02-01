package com.payment.paypal.subscription;

public interface BillingPlanService {

	BillingPlan createAndActivateBillingPlan(BillingPlanRequest billingPlanRequest);

	BillingPlanEntity getBillingPlanBySellersEmail(String email);

}
