package com.payment.commons;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRegistrationRequest {

    private String id;
    private String name;
    private String password;
    private List<PaymentType> paymentTypes;

}
