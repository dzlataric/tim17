package com.payment.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterBankTransactionRequest {

    private int AcquirerOrderId;
    private String AcquirerTimestamp;
    private String transactionId;
    private String primaryAccountNumber;
    private String cardHolderNumber;
    private String cardVerificationNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate cardValidityDate;
    private Currency currency;
    private Double amount;

}
