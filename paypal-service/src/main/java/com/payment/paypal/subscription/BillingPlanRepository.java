package com.payment.paypal.subscription;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingPlanRepository extends JpaRepository<BillingPlanEntity, Long> {

	Optional<BillingPlanEntity> findBySellerId(Long sellerId);

}
