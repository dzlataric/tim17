package com.payment.concentrator.web;

import com.payment.commons.MerchantRegistrationRequest;
import com.payment.commons.MerchantRegistrationResponse;
import com.payment.concentrator.merchant.Merchant;
import com.payment.concentrator.merchant.MerchantConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class MerchantController {

	private final MerchantConfiguration merchantConfiguration;

	@Autowired
	public MerchantController(final MerchantConfiguration merchantConfiguration) {
		this.merchantConfiguration = merchantConfiguration;
	}

	@RequestMapping(value = "/merchants", method = RequestMethod.GET)
	public Map<String, Merchant> getMerchantsConfiguration() {
		log.info("Getting merchants...");
		return merchantConfiguration.getMerchants();
	}

	@RequestMapping(value = "/merchant/register", method = RequestMethod.POST)
	public ResponseEntity<MerchantRegistrationResponse> registerNewMerchantService(@RequestBody final MerchantRegistrationRequest request) {
		log.info("Received request for new merchant service with configuration: {}", request.toString());
		if (!Objects.isNull(merchantConfiguration.getMerchants().get(request.getId()))) {
			log.warn("Merchant service for request {} already registered!", request.toString());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		Merchant newMerchant = new Merchant(request.getId(), request.getName(), request.getPassword(), request.getPaymentTypes());
		merchantConfiguration.getMerchants().put(request.getId(), newMerchant);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}