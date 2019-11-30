package com.payment.seller1.order;

import com.payment.seller1.magazine.Magazine;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {

    private String id;
    private String userId;
    private Magazine magazine;

    public Order(String id, String userId, Magazine magazine) {
        this.id = id;
        this.userId = userId;
        this.magazine = magazine;
    }

}
