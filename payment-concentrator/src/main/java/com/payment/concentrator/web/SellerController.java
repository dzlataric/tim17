package com.payment.concentrator.web;

import com.payment.commons.SellerRegistrationRequest;
import com.payment.commons.SellerRegistrationResponse;
import com.payment.concentrator.seller.Seller;
import com.payment.concentrator.seller.SellerConfiguration;

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
public class SellerController {

	private final SellerConfiguration sellerConfiguration;

	@Autowired
	public SellerController(final SellerConfiguration sellerConfiguration) {
		this.sellerConfiguration = sellerConfiguration;
	}

	@RequestMapping(value = "/sellers", method = RequestMethod.GET)
	public Map<String, Seller> getSellersConfiguration() {
		log.info("Getting sellers...");
		return sellerConfiguration.getSellers();
	}

	@RequestMapping(value = "/seller/register", method = RequestMethod.POST)
	public ResponseEntity<SellerRegistrationResponse> registerNewSellerService(@RequestBody final SellerRegistrationRequest request) {
		log.info("Received request for new seller service with id: {}", request.getId());
		if (!Objects.isNull(sellerConfiguration.getSellers().get(request.getId()))) {
			log.warn("Seller service for request {} already registered!", request.toString());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		Seller newSeller = new Seller(request.getId(), request.getName(), request.getPaymentTypes());
		sellerConfiguration.getSellers().put(request.getId(), newSeller);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/seller/deregister", method = RequestMethod.POST)
	public ResponseEntity<Void> deregister(@RequestBody SellerRegistrationRequest request) {
		log.info("Received request to remove seller service with id: {}", request.getId());
		if (!Objects.isNull(sellerConfiguration.getSellers().get(request.getId()))) {
			sellerConfiguration.getSellers().remove(request.getId());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		log.warn("Seller service for request {} does not exist!", request.toString());
		return ResponseEntity.badRequest().build();
	}
}
