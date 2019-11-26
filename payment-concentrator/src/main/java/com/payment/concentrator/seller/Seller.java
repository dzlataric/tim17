package com.payment.concentrator.seller;

import com.payment.concentrator.payment.PaymentType;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Seller {

    private String id;
    private String name;
    private List<PaymentType> paymentTypes;

    public Seller(String id, String name, List<PaymentType> paymentTypes) {
        this.id = id;
        this.name = name;
        this.paymentTypes = paymentTypes;
    }
}
