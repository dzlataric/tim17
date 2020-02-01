package com.payment.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterBankTransactionResponse {

    private int AcquirerOrderId;
    private String AcquirerTimestamp;
    private int IssuerOrderId;
    private String IssuerTimestamp;
    private String TransactionResult; // TODO: add enum... DECLINED, APPROVED, ...

}
