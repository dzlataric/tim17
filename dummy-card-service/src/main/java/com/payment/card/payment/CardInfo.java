package com.payment.card.payment;

import java.time.LocalDate;
import java.util.UUID;

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
public class CardInfo {

	private UUID transactionId;
	private String cardNumber;
	private LocalDate cardValidityDate;
	private Long cardVerificationNumber;
	private String redirectUrl;

}
