package com.payment.cryptocurrencyservice.web;

import com.bitpay.sdk.Client;
import com.bitpay.sdk.exceptions.InvoiceCreationException;
import com.bitpay.sdk.model.Invoice.Invoice;
import com.payment.cryptocurrencyservice.bitpay.BitpayInvoiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/bitpay")
public class CryptoPaymentController {

    private final Client bitpay;

    @Autowired
    public CryptoPaymentController(final Client bitpay) {
        this.bitpay = bitpay;
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public String createInvoice(@RequestBody final BitpayInvoiceRequest request) throws InvoiceCreationException {
        Invoice invoice = bitpay.createInvoice(new Invoice(request.getPriceAmount(), request.getCurrency()));
        String invoiceUrl = invoice.getUrl();
        log.info("New invoice created: " + invoiceUrl);
        return invoiceUrl;
    }

}
