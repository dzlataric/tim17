package com.payment.card.web;

import com.payment.card.payment.CardInfo;
import com.payment.card.payment.CardPaymentResponse;

import com.payment.commons.PaymentUrlRequest;
import com.payment.commons.PaymentUrlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@RestController
public class CardPaymentController {

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public ResponseEntity<CardPaymentResponse> registerNewPaymentService(@RequestBody final CardInfo request) {
		log.info("Received request for payment: {}", request.toString());
		return ResponseEntity.status(HttpStatus.OK)
			.body(CardPaymentResponse.builder().transactionId(request.getTransactionId()).redirectUrl(request.getRedirectUrl()).build());
	}

	@RequestMapping(value = "/paymentUrl", method = RequestMethod.POST)
	public ResponseEntity<PaymentUrlResponse> getPaymentUrl(@RequestBody final PaymentUrlRequest request) {
		log.info("Received request for new payment: {}", request.toString());

		String url = "/test/payment/successUrl";

		return ResponseEntity.status(HttpStatus.OK)
				.body((PaymentUrlResponse.builder().paymentId(String.valueOf(new Random().nextInt())).paymentUrl(url)).build());
	}

}
