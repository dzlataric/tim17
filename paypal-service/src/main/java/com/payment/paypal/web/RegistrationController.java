package com.payment.paypal.web;

import com.payment.paypal.seller.RegistrationRequest;
import com.payment.paypal.seller.Seller;
import com.payment.paypal.seller.SellerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

	private final SellerService sellerService;

	@PostMapping
	private ResponseEntity<Seller> register(@RequestBody RegistrationRequest request) {
		log.info("Received request to register seller with email {}", request.getEmail());
		return new ResponseEntity<>(sellerService.registerNewSeller(request), HttpStatus.OK);
	}

}
