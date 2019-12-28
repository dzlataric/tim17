package com.payment.commons;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUrlResponse {

    private String paymentId;
    private String paymentUrl;

}
