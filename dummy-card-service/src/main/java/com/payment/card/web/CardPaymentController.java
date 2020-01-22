package com.payment.card.web;

import com.payment.commons.CardPaymentUrlRequest;
import com.payment.commons.CardPaymentUrlResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class CardPaymentController {

	@Value("${spring.application.name}")
	private String name;

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String registerNewPaymentService() {
		log.info("This is bank {}", name);
		return "Welcome to: " + name;
	}

	@RequestMapping(value = "/paymentUrl", method = RequestMethod.POST)
	public ResponseEntity<CardPaymentUrlResponse> getPaymentUrl(@RequestBody final CardPaymentUrlRequest request) {
		log.info("Received request for new payment: {}", request.toString());

		String url = "/test/"+name+"/payment/successUrl";

		return ResponseEntity.status(HttpStatus.OK)
				.body((CardPaymentUrlResponse.builder().paymentId(String.valueOf(new Random().nextInt())).paymentUrl(url)).build());
	}

}
