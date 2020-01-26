package com.payment.concentrator.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

	private String id;
	private String merchantId;
	private String merchantTimestamp;
	private Double amount;
	private String currency;

}
