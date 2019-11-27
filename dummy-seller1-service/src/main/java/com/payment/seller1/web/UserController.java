package com.payment.seller1.web;

import com.payment.seller1.user.User;
import com.payment.seller1.user.UserConfiguration;
import com.payment.seller1.user.UserRegistrationRequest;
import com.payment.seller1.user.UserRegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserConfiguration userConfiguration;

    @Autowired
    public UserController(final UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<UserRegistrationResponse> registerNewUserService(@RequestBody final UserRegistrationRequest request) {
        log.info("Received request for new user registration, userId: {}", request.getId());
        User newUser = new User(request.getId(), request.getFullName(), request.getUsername(), request.getPassword());
        userConfiguration.getUsers().put(request.getId(), newUser);
        return ResponseEntity.status(HttpStatus.OK).body(UserRegistrationResponse.builder().id(request.getId()).fullName(request.getFullName())
                .username(request.getUsername()).password(request.getPassword())
                .count(userConfiguration.getUsers().size()).build());
    }

}
