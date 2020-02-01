package com.payment.paypal.subscription;

import com.payment.paypal.seller.SellerEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@Builder(toBuilder = true)
@Table(name = "BILLING_PLAN")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillingPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "BILLING_PLAN_ID", unique = true)
	private String billingPlanId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "AMOUNT", nullable = false)
	private Double amount;

	@Column(name = "status", nullable = false)
	private BillingPlanStatus status;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private BillingPlanType type;

	@Column(name = "FREQUENCY")
	@Enumerated(EnumType.STRING)
	private Frequency frequency;

	@OneToOne
	private SellerEntity seller;

	@OneToMany(mappedBy = "billingPlan")
	private List<SubscriptionEntity> subscriptions;
}
