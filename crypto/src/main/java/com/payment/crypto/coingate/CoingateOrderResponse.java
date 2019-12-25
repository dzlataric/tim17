package com.payment.crypto.coingate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoingateOrderResponse {

	private String id;
	private String status;
	@JsonProperty(value = "price_currency")
	private String priceCurrency;
	@JsonProperty(value = "price_amount")
	private String priceAmount;
	@JsonProperty(value = "receive_currency")
	private String receiveCurrency;
	@JsonProperty(value = "receive_amount")
	private String receiveAmount;
	@JsonProperty(value = "created_at")
	private String createdAt;
	@JsonProperty(value = "order_id")
	private String orderId;
	@JsonProperty(value = "payment_url")
	private String paymentUrl;
	private String token;

}
