package com.payment.pccservice.config;

import com.payment.commons.web.CRestTemplateWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PCCServiceConfig {

	@Bean
	public CRestTemplateWrapper restTemplate() {
		return new CRestTemplateWrapper();
	}

}
