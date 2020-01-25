package com.payment.commons;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private String transactionId;
    private String merchantId;
    private String merchantOrderId;
    private String merchantTimestamp;
    private Double amount;
    private String currency;
    private String paymentId;
    private String paymentUrl;
    private String status;

}
