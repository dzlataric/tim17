package com.payment.card.payment;

import com.payment.card.client.ClientService;
import com.payment.card.transaction.Transaction;
import com.payment.card.transaction.TransactionService;
import com.payment.card.transaction.TransactionStatus;
import com.payment.commons.InterBankTransactionRequest;
import com.payment.commons.InterBankTransactionResponse;
import com.payment.commons.utils.Math;
import com.payment.commons.web.CRestTemplateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Objects;
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
    private final CRestTemplateWrapper restTemplate;

    public PaymentService(final CRestTemplateWrapper restTemplate,
                          final ClientService clientService,
                          final TransactionService transactionService) {
        this.restTemplate = restTemplate;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    public boolean checkCardDetails(String pan, String chn, String cvv, LocalDate validThru) {
        // TODO: implement account service, search for account, apply it everywhere
        // TODO: implement card entitiy, service, ... account should have list of cards
        var client = clientService.findClientByPAN(pan);
        var account = client.getAccounts().get(0); // TODO: ^^^
        if (Objects.isNull(account)) {
            log.warn("This card number: {} is not associated with any account!", pan);
            return false;
        }

        if (account.checkCardDetails(pan, chn, cvv, validThru)) {
            log.info("Card details are correct!");
            return true;
        }

        return false;
    }

    public Transaction handleTransaction(UUID transactionId, CardDetailsRequest cardDetails) {
        var localTransaction = isPANFromThisBank(cardDetails.getPrimaryAccountNumber());
        var transaction = transactionService.findById(transactionId);

        if (localTransaction) {
            reserveFunds(transaction.getAmount(), cardDetails.getPrimaryAccountNumber(),
                    cardDetails.getCardHolderNumber(), cardDetails.getCardVerificationNumber(),
                    cardDetails.getCardValidityDate());
            transferFunds(transaction);
        } else {
            var ibt = sendInterBankTransactionRequest(transaction, cardDetails);
            if (ibt.getTransactionResult().equals("APPROVED")) {
                transaction.setSuccessUrl("http://localhost:4200/card/payment/success");
                transferFunds(transaction);
            } else {
                transaction.setErrorUrl("/card/payment/error");
                transaction.setFailedUrl("/card/payment/fail");
            }
        }

        return transaction;
    }

    public void transferFunds(Transaction transaction) {
        var merchant = clientService.findClientById(transaction.getMerchantId().toString());
        var amount = transaction.getAmount();
        clientService.addToBalance(amount, merchant, merchant.getAccounts().get(0).getCardPAN());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionService.saveTransaction(transaction);
        clientService.saveClient(merchant);
    }

    public boolean reserveFunds(Double amount, String buyerPAN, String chn, String cvv, LocalDate validThru) {
        if (checkCardDetails(buyerPAN, chn, cvv, validThru)) {
            log.info("Card {} transfer approved!", buyerPAN);
            var buyer = clientService.findClientByPAN(buyerPAN);
            var isSuccessful = clientService.subtractFromBalance(amount, buyer, buyerPAN);
            clientService.saveClient(buyer);
            return isSuccessful;
        } else {
            log.warn("Card {} transfer declined!", buyerPAN);
        }

        return false;
    }

    public boolean isPANFromThisBank(String pan) {
        String check = pan.substring(0, IIN.length());
        return check.equals(IIN);
    }

    public InterBankTransactionResponse handleInterBankTransaction(InterBankTransactionRequest request) {
        var isSuccessful = reserveFunds(request.getAmount(), request.getPrimaryAccountNumber(),
                request.getCardHolderNumber(), request.getCardVerificationNumber(),
                request.getCardValidityDate());
        var ibtResult = isSuccessful ? "APPROVED" : "DENIED";
        return prepareIBTResponse(request, ibtResult);
    }

    public InterBankTransactionResponse sendInterBankTransactionRequest(Transaction transaction, CardDetailsRequest cardDetails) {

        var request = prepareIBTRequest(transaction, cardDetails);

        return restTemplate.post(PCC_URL+PCC_IBT_Endpoint, request,
                InterBankTransactionResponse.class, "Failed posting inter-bank transaction");
    }

    public InterBankTransactionRequest prepareIBTRequest(Transaction transaction, CardDetailsRequest cardDetails) {
        return new InterBankTransactionRequest(Math.generateRandomDigits(10),
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
                Math.generateRandomDigits(10),
                LocalDate.now().toString(),
                transactionResult);
    }

}