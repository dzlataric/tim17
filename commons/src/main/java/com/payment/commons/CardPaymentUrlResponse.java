package com.payment.commons;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardPaymentUrlResponse {

    private Integer transactionId;
    private String merchantId;
    private String paymentId;
    private String paymentUrl;

}
