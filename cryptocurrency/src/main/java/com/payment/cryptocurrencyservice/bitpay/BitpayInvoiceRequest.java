package com.payment.cryptocurrencyservice.bitpay;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BitpayInvoiceRequest {

    private Double priceAmount;
    private String currency;

}
