package com.payment.seller1.config;

import com.payment.commons.PaymentType;
import com.payment.commons.SellerRegistrationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RegisteringConfig {

	@Value("${concentrator.url}")
	private String baseUrl;

	@Value("${concentrator.register}")
	private String registerEndpoint;

	@Value("${concentrator.deregister}")
	private String deregisterEndpoint;

	@Value("${concentrator.payment-types}")
	private String paymentTypes;

	@Value("${registration.id}")
	private String id;

	private final RestTemplate restTemplate;

	@Autowired
	public RegisteringConfig(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// TODO: remove registration and deregistration
//	@PostConstruct
	private void init() {
		log.info("Initializing and registering service...");
		try {
			final var paymentTypes = getPaymentTypes();
			restTemplate.postForObject(baseUrl + registerEndpoint,
				new HttpEntity<>(SellerRegistrationRequest.builder().id(id).name("Seller1").paymentTypes(paymentTypes).build()), Void.class);
		} catch (HttpClientErrorException e) {
			log.error("Failed registering service with error {}", e.getMessage());
			throw e;
		}
	}

//	@PreDestroy
	public void onExit() {
		log.info("Stopping and deregistering service...");
		try {
			restTemplate.postForObject(baseUrl + deregisterEndpoint, new HttpEntity<>(SellerRegistrationRequest.builder().id(id).build()), Void.class);
		} catch (HttpClientErrorException e) {
			log.error("Failed deregistering service with error: ", e);
			throw e;
		}
	}

	private List<PaymentType> getPaymentTypes() {
		return new ArrayList<>(Objects
			.requireNonNull(restTemplate.exchange(baseUrl + paymentTypes, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, PaymentType>>() {
			}).getBody()).values());
	}

}
