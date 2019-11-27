package com.payment.seller1.magazine;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class MagazineConfiguration {

    private Map<String, Magazine> magazines;

}
