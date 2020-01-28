package com.payment.pccservice.web;

import com.payment.commons.InterBankTransactionRequest;
import com.payment.commons.InterBankTransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@RestController
public class InterBankTransactionController {

    private RestTemplate restTemplate;

    @Autowired
    public InterBankTransactionController(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //TODO: impl. backservice

    /** receive request for inter-bank transaction from acquirer and pass it to issuer
     * return response for inter-bank transaction from issuer and pass it to acquirer  */
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public InterBankTransactionResponse invoiceReceiveRequest(@RequestBody final InterBankTransactionRequest request) {
        //TODO: check req then pass it to issuer
        //TODO: CHECK

        var bankURL = "https://localhost:8099"; //TODO: decide using PAN
        var endpoint = "/ibt/invoice";

        var ibt = new InterBankTransactionResponse();

        try {
            ibt = restTemplate.postForObject(bankURL + endpoint,
                    new HttpEntity<>(request), InterBankTransactionResponse.class);
            log.info(Objects.requireNonNull(ibt).toString());
        } catch (HttpClientErrorException e) {
            log.error("Failed posting inter-bank transaction {}", e.getMessage());
            throw e;
        }

        return ibt;
    }

}
