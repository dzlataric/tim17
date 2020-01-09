package com.payment.commons;

import java.util.List;

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
public class MerchantRegistrationResponse {

	private String id;
	private String name;
	private List<PaymentType> paymentTypes;
	private int count;

}
