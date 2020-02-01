package com.scientific.center.magazine;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Magazine {

	private Long id;
	private String title;
	private Integer issn;
	private MembershipFeeType membershipFeeType;
	private List<Editor> editors;
	private List<AreaOfScience> areasOfScience;
	private List<PaymentType> paymentTypes;
}
