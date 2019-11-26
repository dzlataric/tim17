package com.payment.concentrator.web;

import com.payment.concentrator.payment.PaymentType;
import com.payment.concentrator.payment.PaymentTypeRegistrationRequest;
import com.payment.concentrator.payment.PaymentTypeRegistrationResponse;
import com.payment.concentrator.payment.PaymentTypeConfiguration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PaymentTypesController {

	private final PaymentTypeConfiguration paymentTypeConfiguration;

	@Autowired
	public PaymentTypesController(final PaymentTypeConfiguration paymentTypeConfiguration) {
		this.paymentTypeConfiguration = paymentTypeConfiguration;
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public Map<String, PaymentType> getPaymentTypeConfiguration() {
		log.info("Getting payment types...");
		return paymentTypeConfiguration.getPaymentTypes();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<PaymentTypeRegistrationResponse> registerNewPaymentService(@RequestBody final PaymentTypeRegistrationRequest request) {
		log.info("Received request for service with id: {}", request.getId());
		paymentTypeConfiguration.getPaymentTypes().put(request.getId(), request.getPaymentType());
		return ResponseEntity.status(HttpStatus.OK).body(PaymentTypeRegistrationResponse.builder().id(request.getId()).paymentType(request.getPaymentType())
			.count(paymentTypeConfiguration.getPaymentTypes().size()).build());
	}

}
