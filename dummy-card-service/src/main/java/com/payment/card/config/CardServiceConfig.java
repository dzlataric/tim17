package com.payment.card.config;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CardServiceConfig {

	@Value("${concentrator.register.url}")
	private String url;

	@PostConstruct
	private void init() {
		log.info("Initializing service...");
		RestTemplate restTemplate = new RestTemplate();
		final var response = restTemplate.postForObject(url,
			new HttpEntity<>(PaymentTypeRegistrationRequest.builder().id(String.valueOf(new Random().nextInt())).paymentType(PaymentType.CARD).build()),
			PaymentTypeRegistrationResponse.class);
		log.info("Response: {}", response.getCount());
	}

	@PreDestroy
	public void onExit() {
		//TODO deregister
		log.info("###STOPing###");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			log.error("", e);;
		}
		log.info("###STOP FROM THE LIFECYCLE###");
	}

}
