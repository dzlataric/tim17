package com.payment.concentrator.seller;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class SellerConfiguration {

    private Map<String, Seller> sellers;

}
