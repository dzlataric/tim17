package com.payment.seller1.config;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class SellerRegistrationResponse {

    private String id;
    private String name;
    private List<PaymentType> paymentTypes;
    private int count;

}
