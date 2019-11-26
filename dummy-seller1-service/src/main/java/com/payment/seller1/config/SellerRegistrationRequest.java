package com.payment.seller1.config;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class SellerRegistrationRequest {

    private String id;
    private String name;
    private List<PaymentType> paymentTypes;

}
