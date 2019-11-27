package com.payment.seller1.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRegistrationRequest {

    private String id;
    private String fullName;
    private String username;
    private String password;

}
