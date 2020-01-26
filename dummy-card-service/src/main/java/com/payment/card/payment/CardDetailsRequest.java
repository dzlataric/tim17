package com.payment.card.payment;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
