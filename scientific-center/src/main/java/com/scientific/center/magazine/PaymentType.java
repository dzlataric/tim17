package com.scientific.center.magazine;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentType {

	private Long id;
	private String name;

}
