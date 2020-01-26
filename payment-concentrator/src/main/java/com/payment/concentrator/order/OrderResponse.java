package com.payment.concentrator.order;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String merchantId;
    private String merchantTimestamp;
    private Double amount;
    private String currency;
    private String paymentUrl;
    private String status;

}
