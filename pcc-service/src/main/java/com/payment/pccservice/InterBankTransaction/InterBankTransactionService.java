package com.payment.pccservice.InterBankTransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class InterBankTransactionService implements IInterBankTransactionService {

    @Override
    public List<InterBankTransaction> findAllIBTransactions() {
        return null;
    }

}
