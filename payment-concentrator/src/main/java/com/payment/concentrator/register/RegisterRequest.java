package com.payment.concentrator.register;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterRequest {

	private String name;
	private String contactEmail;
	private String returnUrl;

}
