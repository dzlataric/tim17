package com.payment.seller1.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceOrderRequest {

    private String magazineId;
    private String userId;

}
