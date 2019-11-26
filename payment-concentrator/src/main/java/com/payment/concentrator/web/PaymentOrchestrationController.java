package com.payment.concentrator.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentOrchestrationController {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greeting() {
		return HttpStatus.OK.name();
	}

}
