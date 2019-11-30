package com.payment.card.web;

import com.payment.card.payment.CardInfo;
import com.payment.card.payment.CardPaymentResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CardPaymentController {

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String pay(@ModelAttribute CardInfo request) {
		log.info("Received request for payment: {}", request.toString());
		// TODO: after successful payment, attach ACL to users for granting access to magazine
		return "Your payment will be processed...";
	}

}
