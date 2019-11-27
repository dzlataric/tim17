package com.payment.seller1.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Seller1OrchestrationController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting() {
        return HttpStatus.OK.name();
    }

}
