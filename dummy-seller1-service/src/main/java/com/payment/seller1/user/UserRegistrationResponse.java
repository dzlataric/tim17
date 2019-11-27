package com.payment.seller1.user;

import lombok.*;

@Value
@Builder
public class UserRegistrationResponse {

    private String id;
    private String fullName;
    private String username;
    private String password;
    private int count;

}
