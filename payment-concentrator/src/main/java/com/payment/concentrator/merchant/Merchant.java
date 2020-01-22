package com.payment.concentrator.merchant;

import com.payment.commons.PaymentType;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

	private String id;
	private String bankId;
	private String name;
	private String password;
	private List<PaymentType> paymentTypes;

}
