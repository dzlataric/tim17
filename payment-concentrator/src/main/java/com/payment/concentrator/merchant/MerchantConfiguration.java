package com.payment.concentrator.merchant;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class MerchantConfiguration {

    private Map<String, Merchant> merchants;

}
