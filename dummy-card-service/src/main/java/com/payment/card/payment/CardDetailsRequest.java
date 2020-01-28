package com.payment.card.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsRequest {

	private String transactionId;
	@JsonProperty("pan")
	private String primaryAccountNumber;
	@JsonProperty("chn")
	private String cardHolderNumber;
	@JsonProperty("cvv")
	private String cardVerificationNumber;
	@JsonProperty("validThru")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate cardValidityDate;

}
