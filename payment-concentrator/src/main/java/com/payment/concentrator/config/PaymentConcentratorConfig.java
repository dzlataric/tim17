package com.payment.concentrator.config;

import com.payment.commons.PaymentType;
import com.payment.concentrator.payment.PaymentTypeConfiguration;
import com.payment.concentrator.seller.Seller;
import com.payment.concentrator.seller.SellerConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConcentratorConfig {

	private Map<String, PaymentType> paymentTypeConfigurationMap = new HashMap<>();
	private Map<String, Seller> sellersConfigurationMap = new HashMap<>();

	@Bean
	public PaymentTypeConfiguration paymentTypeConfiguration() {
		return PaymentTypeConfiguration.builder().paymentTypes(paymentTypeConfigurationMap).build();
	}

	@Bean
	public SellerConfiguration sellerConfiguration() {
		return SellerConfiguration.builder().sellers(sellersConfigurationMap).build();
	}

}
