package com.payment.paypal.web;

import com.payment.paypal.payment.ExecutePayment;
import com.payment.paypal.payment.PaymentRequest;
import com.payment.paypal.payment.PaymentResponse;
import com.payment.paypal.payment.PaymentService;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/paypal")
public class PaypalController {

	private final PaymentService paymentService;

	@Autowired
	public PaypalController(final PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody final PaymentRequest paymentRequest) throws PayPalRESTException {
		return new ResponseEntity<>(paymentService.createPayment(paymentRequest), HttpStatus.OK);
	}

	@GetMapping(value = "/create/success")
	public ResponseEntity<String> success(@RequestParam final String paymentId, @RequestParam final String token,
		@RequestParam("PayerID") final String payerId) {
		log.info("Success payment from {}, with payment id {} and token {}", payerId, paymentId, token);
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}

	@GetMapping(value = "/create/cancel")
	public ResponseEntity<String> cancel(@RequestParam final String token) {
		log.info("Canceled with token {}", token);
		return new ResponseEntity<>("Canceled!", HttpStatus.OK);
	}

	@PostMapping(value = "/execute", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> executePayment(@RequestBody final ExecutePayment executePayment) throws PayPalRESTException {
		return new ResponseEntity<>(paymentService.executePayment(executePayment).toJSON(), HttpStatus.OK);
	}

}
