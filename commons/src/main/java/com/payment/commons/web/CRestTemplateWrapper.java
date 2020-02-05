package com.payment.commons.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
public class CRestTemplateWrapper extends RestTemplate {

    public <T> T post(String url, Object request, Class<T> responseType, String errorMessage) {
        T response;
        try {
            response = postForObject(url, new HttpEntity<>(request), responseType);
            log.info(Objects.requireNonNull(response).toString());
        } catch (HttpClientErrorException e) {
            log.error(errorMessage + ": " + e.getMessage());
            throw e;
        }

        return response;
    }

}
