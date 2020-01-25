package com.payment.card.transaction;

import java.util.List;

public interface ITransactionService {

    List<Transaction> findAllTransactions();
    void saveTransaction(Transaction transaction);

}
