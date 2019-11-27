package com.payment.seller1.user;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class UserConfiguration {

    private Map<String, User> users;

}
