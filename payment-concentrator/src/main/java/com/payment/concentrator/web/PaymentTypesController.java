package com.payment.concentrator.web;

import com.payment.commons.PaymentType;
import com.payment.commons.PaymentTypeRegistrationRequest;
import com.payment.concentrator.payment.PaymentTypeConfiguration;

import java.util.Map;
import java.util.Objects;

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

	@RequestMapping(value = "/payment/types", method = RequestMethod.GET)
	public Map<String, PaymentType> getPaymentTypeConfiguration() {
		log.info("Getting payment types...");
		return paymentTypeConfiguration.getPaymentTypes();
	}

	@RequestMapping(value = "/payment/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registerNewPaymentService(@RequestBody final PaymentTypeRegistrationRequest request) {
		log.info("Received request for new payment service with configuration: {}", request.toString());
		if (!Objects.isNull(paymentTypeConfiguration.getPaymentTypes().get(request.getId()))) {
			log.warn("Payment service for request {} already registered!", request.toString());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		paymentTypeConfiguration.getPaymentTypes().put(request.getId(), request.getPaymentType());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/payment/deregister", method = RequestMethod.POST)
	public ResponseEntity<Void> deregister(@RequestBody PaymentTypeRegistrationRequest request) {
		log.info("Received request to remove payment service with configuration: {}", request.toString());
		if (!Objects.isNull(paymentTypeConfiguration.getPaymentTypes().get(request.getId()))) {
			paymentTypeConfiguration.getPaymentTypes().remove(request.getId());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		log.warn("Payment service for request {} does not exist!", request.toString());
		return ResponseEntity.badRequest().build();
	}

}
