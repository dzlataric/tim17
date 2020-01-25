package com.payment.concentrator.web;

import com.payment.commons.MerchantRegistrationRequest;
import com.payment.commons.MerchantRegistrationResponse;
import com.payment.concentrator.merchant.Merchant;
import com.payment.concentrator.merchant.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class MerchantController {

	private final MerchantService merchantService;

	@Autowired
	public MerchantController(final MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	@RequestMapping(value = "/merchants", method = RequestMethod.GET)
	public List<Merchant> getMerchantsConfiguration() {
		log.info("Getting merchants...");
		return merchantService.findAllMerchants();
	}

	@RequestMapping(value = "/merchant/register", method = RequestMethod.POST)
	public ResponseEntity<MerchantRegistrationResponse> registerNewMerchantService(@RequestBody final MerchantRegistrationRequest request) {
		log.info("Received request for new merchant service with configuration: {}", request.toString());
		if (!Objects.isNull(merchantService.findAllMerchants())) {
			log.warn("Merchant service for request {} already registered!", request.toString());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		Merchant newMerchant = new Merchant(request.getId(),"bankIDTEST", request.getName(), request.getPassword(), request.getPaymentTypes());
		merchantService.saveMerchant(newMerchant);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
