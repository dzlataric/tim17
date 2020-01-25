package com.payment.card.transaction;

import com.payment.commons.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private UUID transactionId;
    private UUID merchantId;
    private UUID merchantOrderId;
    private String merchantTimestamp; // TODO: datetime
    private Double amount;
    private Currency currency;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;
    private TransactionStatus status;

}
