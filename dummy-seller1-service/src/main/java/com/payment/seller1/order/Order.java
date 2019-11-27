package com.payment.seller1.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {

    private String id;
    private String magazineId;
    private String userId;
    private Float price;

    public Order(String id, String magazineId, String userId, Float price) {
        this.id = id;
        this.magazineId = magazineId;
        this.userId = userId;
        this.price = price;
    }

}
