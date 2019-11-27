package com.payment.seller1.order;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class OrderConfiguration {

    private Map<String, Order> orders;

}
