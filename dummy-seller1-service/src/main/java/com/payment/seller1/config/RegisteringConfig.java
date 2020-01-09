package com.payment.seller1.config;

import com.payment.commons.PaymentType;
import com.payment.commons.MerchantRegistrationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

	@Value("${concentrator.payment-types}")
	private String paymentTypes;

	@Value("${registration.id}")
	private String id;

	private final RestTemplate restTemplate;

	@Autowired
	public RegisteringConfig(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private List<PaymentType> getPaymentTypes() {
		return new ArrayList<>(Objects
			.requireNonNull(restTemplate.exchange(baseUrl + paymentTypes, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, PaymentType>>() {
			}).getBody()).values());
	}

}
