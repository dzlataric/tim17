package com.payment.card.web;

import com.payment.card.payment.CardInfo;
import com.payment.card.payment.CardPaymentResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CardPaymentController {

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public ResponseEntity<CardPaymentResponse> registerNewPaymentService(@RequestBody final CardInfo request) {
		log.info("Received request for payment: {}", request.toString());
		return ResponseEntity.status(HttpStatus.OK)
			.body(CardPaymentResponse.builder().transactionId(request.getTransactionId()).redirectUrl(request.getRedirectUrl()).build());
	}

}
