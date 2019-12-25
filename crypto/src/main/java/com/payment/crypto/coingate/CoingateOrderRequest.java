package com.payment.crypto.coingate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CoingateOrderRequest {

	@JsonProperty(value = "order_id")
	private String orderId;
	private String title;
	@JsonProperty(value = "merchant_id")
	private String merchantId;
	private String description;
	@JsonProperty(value = "price_amount")
	private Double priceAmount;
	@JsonProperty(value = "price_currency")
	private String priceCurrency;
	@JsonProperty(value = "receive_currency")
	private String receiveCurrency;
	@JsonProperty(value = "callback_url")
	private String callbackUrl;
	@JsonProperty(value = "cancel_url")
	private String cancelUrl;
	@JsonProperty(value = "success_url")
	private String successUrl;

}
