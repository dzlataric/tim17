package com.payment.pccservice.web;

import com.payment.commons.InterBankTransactionRequest;
import com.payment.commons.InterBankTransactionResponse;
import com.payment.commons.web.CRestTemplateWrapper;
import com.payment.pccservice.bank.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InterBankTransactionController {

    @Value("${ibt.endpoint}")
    private String ibtEndpoint;

    @Value("${iin.length}")
    private int iinLength;

    private CRestTemplateWrapper restTemplate;

    private BankService bankService;

    @Autowired
    public InterBankTransactionController(final CRestTemplateWrapper restTemplate,
                                          final BankService bankService) {
        this.restTemplate = restTemplate;
        this.bankService = bankService;
    }

    //TODO: impl. backservice

    /**
     * receive request for inter-bank transaction from acquirer and pass it to issuer
     * then return response from issuer and pass it to acquirer
     */
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public InterBankTransactionResponse invoiceReceiveRequest(@RequestBody final InterBankTransactionRequest request) {
        //TODO: check request

        var iin = request.getPrimaryAccountNumber().substring(0, iinLength);
        var bankURL = bankService.findBankByIIN(iin).getPaymentUrl();

        return restTemplate.post(bankURL + ibtEndpoint, request,
                InterBankTransactionResponse.class, "Failed posting inter-bank transaction");
    }

}
