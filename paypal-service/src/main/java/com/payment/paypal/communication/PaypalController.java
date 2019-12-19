package com.payment.paypal.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/paypal")
public class PaypalController {

	@GetMapping()
	public ResponseEntity<PaypalTokenResponse> getPaypalResponse() throws UnsupportedEncodingException, JsonProcessingException {
		final var restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBasicAuth("client_id", "secret");

		final var url = "https://api.sandbox.paypal.com/v1/oauth2/token?" + URLEncoder.encode("grant_type", StandardCharsets.UTF_8)
			+ "=" + URLEncoder.encode("client_credentials", StandardCharsets.UTF_8);
		final var stringResponse = restTemplate.postForObject(url, new HttpEntity<>(Void.TYPE, headers), String.class);
		assert stringResponse != null;
		return ResponseEntity.ok(new ObjectMapper().readValue(stringResponse, PaypalTokenResponse.class));
	}

}
