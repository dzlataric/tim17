package com.payment.concentrator.payment;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
class PaymentOrchestrationServiceImpl implements PaymentOrchestrationService {

	private final EurekaClient eurekaClient;
	private final DiscoveryClient discoveryClient;

	@Autowired
	public PaymentOrchestrationServiceImpl(final EurekaClient eurekaClient, final DiscoveryClient discoveryClient) {
		this.eurekaClient = eurekaClient;
		this.discoveryClient = discoveryClient;
	}

	private void a() {
		final var applications = eurekaClient.getApplications().getRegisteredApplications();
	}

}
