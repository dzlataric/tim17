package com.payment.concentrator.seller;

import com.payment.concentrator.payment.PaymentType;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class SellerRegistrationResponse {

    private String id;
    private String name;
    private List<PaymentType> paymentTypes;
    private int count;

}
