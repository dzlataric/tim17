package com.payment.pccservice.InterBankTransaction;

import java.util.List;

public interface IInterBankTransactionService {

    List<InterBankTransaction> findAllIBTransactions();

}
