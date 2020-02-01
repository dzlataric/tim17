package com.payment.card.payment;

import com.payment.card.client.Client;
import com.payment.card.client.ClientService;
import com.payment.card.transaction.Transaction;
import com.payment.card.transaction.TransactionService;
import com.payment.card.transaction.TransactionStatus;
import com.payment.commons.InterBankTransactionRequest;
import com.payment.commons.InterBankTransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class PaymentService {

    @Value("${pcc.base.url}")
    private String PCC_URL;

    @Value("${pcc.ibt.endpoint}")
    private String PCC_IBT_Endpoint;

    @Value("${bank.iin}")
    private String IIN;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final TransactionService transactionService;

    @Autowired
    private final RestTemplate restTemplate;

    public PaymentService(final RestTemplate restTemplate,
                          final ClientService clientService,
                          final TransactionService transactionService) {
        this.restTemplate = restTemplate;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    public void handleTransaction(UUID transactionId, CardDetailsRequest cardDetails) {
        var localTransaction = isPANFromThisBank(cardDetails.getPrimaryAccountNumber());
        var transaction = transactionService.findById(transactionId);

        if (localTransaction) {
            reserveFunds(transaction.getAmount(), cardDetails.getPrimaryAccountNumber());
            transferFunds(transaction);
        } else {
//          TODO: implement
            var ibt = sendInterBankTransactionRequest(transaction, cardDetails);
            transferFunds(transaction); //TODO: CHECK
//          TRANSACTION
//          check and reservation of funds on buyers account
//          finish transaction by moving funds to seller
//          prepare redirect url and add it to response
        }
    }


    //TODO: Fix params
    public void transferFunds(Transaction transaction) {
        var merchant = clientService.findClientById(transaction.getMerchantId().toString());
        var amount = transaction.getAmount();
        clientService.addToBalance(amount, merchant, merchant.getAccounts().get(0).getCardPAN());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionService.saveTransaction(transaction);
        clientService.saveClient(merchant);
    }

    public boolean reserveFunds(Double amount, String buyerPAN) {
        var buyer = clientService.findClientByPAN(buyerPAN);
        var isSuccessful = clientService.subtractFromBalance(amount, buyer, buyerPAN);
        clientService.saveClient(buyer);
        return isSuccessful;
    }

    public boolean isPANFromThisBank(String pan) {
        String check = pan.substring(0, IIN.length());
        return check.equals(IIN);
    }

    public InterBankTransactionResponse handleInterBankTransaction(InterBankTransactionRequest request) {
        var isSuccessful = reserveFunds(request.getAmount(), request.getPrimaryAccountNumber());
        var ibtResult = isSuccessful ? "APPROVED" : "DENIED";
        return prepareIBTResponse(request, ibtResult);
    }

    public InterBankTransactionResponse sendInterBankTransactionRequest(Transaction transaction, CardDetailsRequest cardDetails) {

        var request = prepareIBTRequest(transaction, cardDetails);

        var ibt = new InterBankTransactionResponse();

        try {
            ibt = restTemplate.postForObject(PCC_URL + PCC_IBT_Endpoint,
                    new HttpEntity<>(request), InterBankTransactionResponse.class);
            log.info(Objects.requireNonNull(ibt).toString());
        } catch (HttpClientErrorException e) {
            log.error("Failed posting inter-bank transaction {}", e.getMessage());
            throw e;
        }

        return ibt;
    }

    public InterBankTransactionRequest prepareIBTRequest(Transaction transaction, CardDetailsRequest cardDetails) {
        return new InterBankTransactionRequest(generateRandomDigits(10),
                LocalDate.now().toString(),
                transaction.getTransactionId().toString(),
                cardDetails.getPrimaryAccountNumber(),
                cardDetails.getCardHolderNumber(),
                cardDetails.getCardVerificationNumber(),
                cardDetails.getCardValidityDate(),
                transaction.getCurrency(),
                transaction.getAmount());
    }

    public InterBankTransactionResponse prepareIBTResponse(InterBankTransactionRequest request, String transactionResult) {
        return new InterBankTransactionResponse(request.getAcquirerOrderId(),
                request.getAcquirerTimestamp(),
                generateRandomDigits(10),
                LocalDate.now().toString(),
                transactionResult);
    }

    //TODO: MOVE TO COMMONS UTIL
    /**
     * Generates a random int with n digits
     */
    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

}