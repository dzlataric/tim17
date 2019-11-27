package com.payment.seller1.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    private String id;
    private String fullName;
    private String username;
    private String password;

    public User(String id, String fullName, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

}
