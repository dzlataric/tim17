package com.payment.concentrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConcentratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcentratorApplication.class, args);
	}

}
