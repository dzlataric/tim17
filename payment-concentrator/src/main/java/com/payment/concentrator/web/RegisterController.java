package com.payment.concentrator.web;

import com.payment.concentrator.register.RegisterService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/register")
@AllArgsConstructor
public class RegisterController {

	private final RegisterService registerService;

}
