package com.payment.card.config;

import com.payment.commons.PaymentType;
import com.payment.commons.PaymentTypeRegistrationRequest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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
			restTemplate.postForObject(baseUrl + registerEndpoint,
				new HttpEntity<>(PaymentTypeRegistrationRequest.builder().id(id).url("https://localhost:8080")
					.paymentType(PaymentType.CARD).build()), Void.class);
		} catch (HttpClientErrorException e) {
			log.error("Failed registering service with error {}", e.getMessage());
			throw e;
		}
	}

//	@PreDestroy
	public void onExit() {
		log.info("Stopping and deregistering service...");
		try {
			restTemplate.postForObject(baseUrl + deregisterEndpoint, new HttpEntity<>(PaymentTypeRegistrationRequest.builder().id(id).build()), Void.class);
		} catch (HttpClientErrorException e) {
			log.error("Failed deregistering service with error: ", e);
			throw e;
		}
	}

}
