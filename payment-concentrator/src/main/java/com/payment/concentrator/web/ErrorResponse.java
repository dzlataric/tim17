package com.payment.concentrator.web;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
class ErrorResponse {

	private final int code;
	private final String message;

}
