package com.payment.card.payment;

import com.payment.card.client.ClientService;
import com.payment.card.transaction.Transaction;
import com.payment.card.transaction.TransactionService;
import com.payment.card.transaction.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Value("${bank.iin}")
    private String IIN;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final TransactionService transactionService;

    public PaymentService(final ClientService clientService, final TransactionService transactionService) {
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    public void handleTransaction(UUID transactionId, String buyerPAN) {
        var localTransaction = isPANFromThisBank(buyerPAN);
        if (localTransaction) {
            transferFunds(transactionId, buyerPAN);
        } else {
//          TODO: implement
//          send request to PCC case B,
//          TRANSACTION
//          check and reservation of funds on buyers account
//          finish transaction by moving funds to seller
//          prepare redirect url and add it to response
        }
    }

    public void transferFunds(UUID transactionId, String buyerPAN) {
        var transaction = transactionService.findById(transactionId);
        var merchant = clientService.findClientById(transaction.getMerchantId().toString());
        var amount = transaction.getAmount();
        var buyer = clientService.findClientByPAN(buyerPAN);
        clientService.subtractFromBalance(amount, buyer, buyerPAN);
        clientService.addToBalance(amount, merchant, merchant.getAccounts().get(0).getCardPAN());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionService.saveTransaction(transaction);
        clientService.saveClient(buyer);
        clientService.saveClient(merchant);
    }

    public boolean isPANFromThisBank(String pan){
        String check = pan.substring(0, IIN.length());
        return check.equals(IIN);
    }

}