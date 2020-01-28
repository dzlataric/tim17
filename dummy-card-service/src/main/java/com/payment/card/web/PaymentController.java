package com.payment.card.web;

import com.payment.card.client.ClientService;
import com.payment.card.payment.CardDetailsRequest;
import com.payment.card.payment.PaymentService;
import com.payment.card.transaction.Transaction;
import com.payment.card.transaction.TransactionService;
import com.payment.card.transaction.TransactionStatus;
import com.payment.commons.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class PaymentController {

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final TransactionService transactionService;

    public PaymentController(final PaymentService paymentService,
                             final ClientService clientService,
                             final TransactionService transactionService) {
        this.paymentService = paymentService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String registerNewPaymentService() {
        log.info("This is bank {}", name);
        return "Welcome to: " + name;
    }

    @RequestMapping(value = "/paymentUrl", method = RequestMethod.POST)
    public ResponseEntity<TransactionResponse> getPaymentUrl(@RequestBody final TransactionRequest request) {
        log.info("Received request for new payment: {}", request.toString());

        String url = "http://localhost:1999/card/payment";
        request.setMerchantOrderId(UUID.randomUUID().toString()); //TODO: for testing remove
        transactionService.saveTransaction(new Transaction(UUID.fromString(request.getTransactionId()),
                UUID.fromString(request.getMerchantId()), UUID.fromString(request.getMerchantOrderId()),
                request.getMerchantTimestamp(), request.getAmount(), Currency.valueOf(request.getCurrency()), request.getSuccessUrl(),
                request.getFailedUrl(), request.getErrorUrl(), TransactionStatus.PENDING));

        return ResponseEntity.status(HttpStatus.OK).body(
                new TransactionResponse(request.getTransactionId(), request.getMerchantId(),
                        "merchantOrderId", "timestamp", 123.00,
                        "currency", "testPaymentId", url, ""));
    }

    @RequestMapping(value = "/card/payment", method = RequestMethod.POST)
    public HttpStatus createPayment(@RequestBody final CardDetailsRequest request) {
        log.info("New payment request: {}", request.toString());

        paymentService.handleTransaction(UUID.fromString(request.getTransactionId()), request);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/ibt/invoice", method = RequestMethod.POST)
    public InterBankTransactionResponse ibtInvoice(@RequestBody final InterBankTransactionRequest request)
    {
        return paymentService.handleInterBankTransaction(request);
    }

}
