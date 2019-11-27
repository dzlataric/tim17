package com.payment.seller1.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceOrderResponse {

    private String id;
    private String magazineId;
    private String userId;
    private Float price;
    private int count;

}
