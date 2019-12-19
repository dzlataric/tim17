package com.payment.paypal.communication;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaypalTokenResponse {

	private String scope;
	@JsonProperty(value = "access_token")
	private String accessToken;
	@JsonProperty(value = "token_type")
	private String tokenType;
	@JsonProperty(value = "app_id")
	private String appId;
	@JsonProperty(value = "expires_in")
	private Long expiresIn;
	private String nonce;

}
