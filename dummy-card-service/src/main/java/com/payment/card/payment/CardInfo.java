package com.payment.card.payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CardInfo {

	private String cardNumber;
	private String cardVerificationCode;
	private String cardValidityDate;
	private String ownerName;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardVerificationCode() {
		return cardVerificationCode;
	}

	public void setCardVerificationCode(String cardVerificationCode) {
		this.cardVerificationCode = cardVerificationCode;
	}

	public String getCardValidityDate() {
		return cardValidityDate;
	}

	public void setCardValidityDate(String cardValidityDate) {
		this.cardValidityDate = cardValidityDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
