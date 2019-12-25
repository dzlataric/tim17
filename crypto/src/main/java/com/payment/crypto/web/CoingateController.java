package com.payment.crypto.web;

import com.payment.crypto.coingate.CoingateOrderRequest;
import com.payment.crypto.coingate.CoingateOrderResponse;
import com.payment.crypto.coingate.CoingateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/coingate")
public class CoingateController {

	private final CoingateService coingateService;

	@Autowired
	public CoingateController(final CoingateService coingateService) {
		this.coingateService = coingateService;
	}

	@PostMapping
	public ResponseEntity<CoingateOrderResponse> createOrder(@RequestBody final CoingateOrderRequest request) {
		return new ResponseEntity<>(coingateService.createOrder(request), HttpStatus.OK);
	}

}
