package com.payment.crypto.coingate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CoingateServiceImpl implements CoingateService {

	@Value("${payment.api.url}")
	private String url;

	@Value("${payment.api.token}")
	private String token;

	private final RestTemplate restTemplate;

	@Autowired
	public CoingateServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public CoingateOrderResponse createOrder(final CoingateOrderRequest request) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Authorization", "Bearer " + token);
		final var response = restTemplate.postForEntity(url + "/orders", new HttpEntity<>(request, requestHeaders), CoingateOrderResponse.class);
		if (HttpStatus.OK == response.getStatusCode()) {
			return response.getBody();
		}
		throw new RuntimeException("Error while creating order!");
	}
}
