package com.payment.concentrator.config;

import com.payment.concentrator.payment.PaymentType;
import com.payment.concentrator.payment.PaymentTypeConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConcentratorConfig {

	private Map<String, PaymentType> paymentTypeConfigurationMap = new HashMap<>();

	@Bean
	public PaymentTypeConfiguration paymentTypeConfiguration() {
		return PaymentTypeConfiguration.builder().paymentTypes(paymentTypeConfigurationMap).build();
	}

}
