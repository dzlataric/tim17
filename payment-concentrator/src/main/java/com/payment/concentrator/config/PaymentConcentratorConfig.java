package com.payment.concentrator.config;

import com.payment.commons.PaymentType;
import com.payment.concentrator.payment.PaymentTypeConfiguration;
import com.payment.concentrator.merchant.Merchant;
import com.payment.concentrator.merchant.MerchantConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConcentratorConfig {

	private Map<String, PaymentType> paymentTypeConfigurationMap = new HashMap<>();
	private Map<String, Merchant> merchantsConfigurationMap = new HashMap<>();

	@Bean
	public PaymentTypeConfiguration paymentTypeConfiguration() {
		return PaymentTypeConfiguration.builder().paymentTypes(paymentTypeConfigurationMap).build();
	}

	@Bean
	public MerchantConfiguration merchantConfiguration() {
		return MerchantConfiguration.builder().merchants(merchantsConfigurationMap).build();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
