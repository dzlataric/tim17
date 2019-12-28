package com.payment.concentrator.order;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Integer id;
    private String merchantId;
    private Double amount;

}
