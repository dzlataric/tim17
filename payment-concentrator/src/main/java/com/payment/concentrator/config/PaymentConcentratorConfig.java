package com.payment.concentrator.config;

import com.payment.commons.PaymentType;
import com.payment.concentrator.payment.PaymentTypeConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaymentConcentratorConfig {

	private Map<String, PaymentType> paymentTypeConfigurationMap = new HashMap<>();

	@Bean
	public PaymentTypeConfiguration paymentTypeConfiguration() {
		return PaymentTypeConfiguration.builder().paymentTypes(paymentTypeConfigurationMap).build();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
