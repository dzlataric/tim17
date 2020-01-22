package com.payment.concentrator.web;

import com.payment.commons.CardPaymentUrlRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class PaymentOrchestrationController {

	private final MerchantConfiguration merchantConfiguration;
	private final RestTemplate restTemplate;

	@Value("${paymentUrl.endpoint}")
	private String paymentUrlEndpoint;

	@Autowired
	public PaymentOrchestrationController(final MerchantConfiguration merchantConfiguration, final RestTemplate restTemplate) {
		this.merchantConfiguration = merchantConfiguration;
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/card/paymentUrl", method = RequestMethod.POST)
	public ResponseEntity<OrderResponse> getPaymentUrl(@RequestBody final OrderRequest request) {
		log.info("Received new order request: {}", request.toString());
		Merchant merchant = merchantConfiguration.getMerchants().get(request.getMerchantId());
//		if (Objects.isNull(merchant)) { //TODO: add test data
//			log.warn("Merchant {} is not registered!", request.getMerchantId());
//			return ResponseEntity.status(HttpStatus.OK).build();
//		}

		CardPaymentUrlRequest paymentUrlRequest = new CardPaymentUrlRequest();
		paymentUrlRequest.setTransactionId(UUID.randomUUID().toString());
//		paymentUrlRequest.setMerchantId(request.getMerchantId());
//		paymentUrlRequest.setMerchantPassword(merchant.getPassword());
		paymentUrlRequest.setAmount(request.getAmount());
		paymentUrlRequest.setMerchantOrderId(String.valueOf(request.getId()));
		paymentUrlRequest.setMerchantTimestamp(request.getMerchantTimestamp());
		paymentUrlRequest.setSuccessUrl("/success");
		paymentUrlRequest.setFailedUrl("/failed");
		paymentUrlRequest.setErrorUrl("/error");

		//String bankUrl = merchant.getBankId(); //TODO: this should target bank acquirer
		String bankUrl = "https://localhost:8080";//TODO: for testing remove

		try {
			restTemplate.postForObject(bankUrl + paymentUrlEndpoint,
					new HttpEntity<>(paymentUrlRequest), Void.class);
		} catch (HttpClientErrorException e) {
			log.error("Failed posting payment url request {}", e.getMessage());
			throw e;
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
