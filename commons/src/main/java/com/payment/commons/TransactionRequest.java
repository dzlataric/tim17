package com.payment.commons;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String transactionId;
    private String merchantId;
    private String merchantPassword;
    private String merchantOrderId;
    private String merchantTimestamp;
    private Double amount;
    private String currency;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
