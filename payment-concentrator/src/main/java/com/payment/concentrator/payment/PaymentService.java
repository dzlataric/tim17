package com.payment.concentrator.payment;

import com.payment.commons.TransactionRequest;
import com.payment.commons.TransactionResponse;
import com.payment.commons.web.CRestTemplateWrapper;
import com.payment.concentrator.bank.Bank;
import com.payment.concentrator.bank.BankService;
import com.payment.concentrator.merchant.Merchant;
import com.payment.concentrator.order.OrderRequest;
import com.payment.concentrator.order.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class PaymentService {

    private final CRestTemplateWrapper restTemplate;

    private final BankService bankService;

    @Value("${paymentUrl.endpoint}")
    private String paymentUrlEndpoint;

    @Autowired
    public PaymentService(final CRestTemplateWrapper restTemplate,
                          final BankService bankService) {
        this.restTemplate = restTemplate;
        this.bankService = bankService;
    }

    public OrderResponse order(OrderRequest request, Merchant merchant) {
        UUID transactionId = UUID.randomUUID();
        var transaction = startTransaction(request, merchant, transactionId);

        return new OrderResponse(transactionId.toString(),
                request.getMerchantId(), request.getMerchantTimestamp(),
                request.getAmount(), request.getCurrency(),
                transaction.getPaymentUrl(), "PAID"); //TODO: add enum for this status
    }

    public TransactionResponse startTransaction(OrderRequest request, Merchant merchant, UUID transactionId) {

        var paymentUrlRequest = getTransactionRequest(request, merchant, transactionId);

        var bank = bankService.findBankById(merchant.getBankId());
        String bankUrl = bank.getPaymentUrl();

        return restTemplate.post(bankUrl + paymentUrlEndpoint, paymentUrlRequest,
                TransactionResponse.class, "Failed posting payment url request");
    }

    public TransactionRequest getTransactionRequest(OrderRequest request, Merchant merchant, UUID transactionId) {
        TransactionRequest paymentUrlRequest = new TransactionRequest();
        paymentUrlRequest.setTransactionId(transactionId.toString());
        paymentUrlRequest.setMerchantId(request.getMerchantId());
        paymentUrlRequest.setMerchantPassword(merchant.getPassword());
        paymentUrlRequest.setAmount(request.getAmount());
        paymentUrlRequest.setCurrency(request.getCurrency());
        paymentUrlRequest.setMerchantOrderId(String.valueOf(request.getId()));
        paymentUrlRequest.setMerchantTimestamp(request.getMerchantTimestamp());
        paymentUrlRequest.setSuccessUrl("/success");
        paymentUrlRequest.setFailedUrl("/failed");
        paymentUrlRequest.setErrorUrl("/error");

        return paymentUrlRequest;
    }

}
