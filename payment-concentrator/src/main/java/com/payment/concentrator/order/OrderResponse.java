package com.payment.concentrator.order;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Integer id;
    private String merchantId;
    private Double amount;
    private String currency;
    private String merchantTimestamp;

}
