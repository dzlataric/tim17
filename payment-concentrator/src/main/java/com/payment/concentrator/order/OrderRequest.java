package com.payment.concentrator.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

	private Integer id;
	private String merchantId;
	private Double amount;
	private String currency;
	private String intent;
	private String paymentType;
	private String successUrl;
	private String failedUrl;
}
