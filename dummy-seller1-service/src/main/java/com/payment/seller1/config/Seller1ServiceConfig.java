package com.payment.seller1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class Seller1ServiceConfig {

    @Value("${concentrator.register.url}")
    private String url;

    @PostConstruct
    private void init() {
        log.info("Initializing service...");

        // get types from concentrator
        List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
        paymentTypes.add(PaymentType.CARD);

        RestTemplate restTemplate = new RestTemplate();
        final var response = restTemplate.postForObject(url,
                new HttpEntity<>(SellerRegistrationRequest.builder().id(String.valueOf(new Random().nextInt())).name("Seller1").paymentTypes(paymentTypes).build()),
                SellerRegistrationResponse.class);
        log.info("Response: {}", response.getCount());
    }

}
