package com.payment.commons;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardPaymentUrlRequest {

    private String transactionId;
    private String merchantId;
    private String merchantPassword; // TODO: assing password to merchant (bank) registration process
    private Double amount;
//    private String currency;
    private String merchantOrderId;
    private String merchantTimestamp;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
