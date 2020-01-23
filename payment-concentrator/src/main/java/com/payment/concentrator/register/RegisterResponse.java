package com.payment.concentrator.register;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterResponse {

	private UUID id;

}
