package com.payment.concentrator.web;

import com.payment.commons.PaymentUrlRequest;
import com.payment.concentrator.merchant.Merchant;
import com.payment.concentrator.merchant.MerchantConfiguration;
import com.payment.concentrator.order.OrderRequest;
import com.payment.concentrator.order.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RestController
public class PaymentOrchestrationController {

	private final MerchantConfiguration merchantConfiguration;
	private final RestTemplate restTemplate;

	@Value("${card.service.url}")
	private String baseUrl;

	@Value("${paymentUrl.endpoint}")
	private String paymentUrlEndpoint;

	@Autowired
	public PaymentOrchestrationController(final MerchantConfiguration merchantConfiguration, final RestTemplate restTemplate) {
		this.merchantConfiguration = merchantConfiguration;
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greeting() {
		return HttpStatus.OK.name();
	}

	@RequestMapping(value = "/card/paymentUrl", method = RequestMethod.POST)
	public ResponseEntity<OrderResponse> getPaymentUrl(@RequestBody final OrderRequest request) {
		log.info("Received new order request: {}", request.toString());
		Merchant merchant = merchantConfiguration.getMerchants().get(request.getMerchantId());
		if (Objects.isNull(merchant)) {
			log.warn("Merchant {} is not registered!", request.getMerchantId());
			return ResponseEntity.status(HttpStatus.OK).build();
		}

		PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
		paymentUrlRequest.setMerchantId(request.getMerchantId());
		paymentUrlRequest.setMerchantPassword(merchant.getPassword());
		paymentUrlRequest.setAmount(request.getAmount());
		paymentUrlRequest.setMerchantOrderID(request.getId());
		paymentUrlRequest.setMerchantTimestamp(LocalDateTime.now());
		paymentUrlRequest.setSuccessUrl("/success");
		paymentUrlRequest.setFailedUrl("/failed");
		paymentUrlRequest.setErrorUrl("/error");

		try {
			restTemplate.postForObject(baseUrl + paymentUrlEndpoint,
					new HttpEntity<>(paymentUrlRequest), Void.class);
		} catch (HttpClientErrorException e) {
			log.error("Failed posting payment url request {}", e.getMessage());
			throw e;
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
