package com.payment.concentrator.web;

import com.payment.concentrator.merchant.MerchantService;
import com.payment.concentrator.order.OrderRequest;
import com.payment.concentrator.order.OrderResponse;
import com.payment.concentrator.payment.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class PaymentOrchestrationController {

	private final MerchantService merchantService;
	private final PaymentService paymentService;

	@Autowired
	public PaymentOrchestrationController(final MerchantService merchantService,
										  final PaymentService paymentService) {
		this.merchantService = merchantService;
		this.paymentService = paymentService;
	}

	@RequestMapping(value = "/card/paymentUrl", method = RequestMethod.POST)
	public ResponseEntity<OrderResponse> getPaymentUrl(@RequestBody final OrderRequest request) {
		log.info("Received new order request: {}", request.toString());
		var merchant = merchantService.getMerchantById(request.getMerchantId());
		if (Objects.isNull(merchant)) {
			log.warn("Merchant {} is not registered!", request.getMerchantId());
			return ResponseEntity.status(HttpStatus.OK).build();
		}

		var orderResponse = paymentService.order(request, merchant);
		return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
	}

}
