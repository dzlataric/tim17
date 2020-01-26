package com.payment.card.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;

    @Autowired
    public TransactionService(final ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll()
                .stream().map(t ->
                        new Transaction(
                                t.getTransactionId(),
                                t.getMerchantId(),
                                t.getMerchantOrderId(),
                                t.getMerchantTimestamp(),
                                t.getAmount(),
                                t.getCurrency(),
                                t.getSuccessUrl(),
                                t.getFailedUrl(),
                                t.getErrorUrl(),
                                t.getStatus())
                ).collect(Collectors.toList());
    }

    @Override
    public void saveTransaction(Transaction t) {
        transactionRepository.save(new TransactionEntity(t.getTransactionId(),
                t.getMerchantId(),
                t.getMerchantOrderId(),
                t.getMerchantTimestamp(),
                t.getAmount(),
                t.getCurrency(),
                t.getSuccessUrl(),
                t.getFailedUrl(),
                t.getErrorUrl(),
                t.getStatus()));
    }

    public Transaction findById(UUID transactionId) {
        for (Transaction t : findAllTransactions()) {
            if (t.getTransactionId().equals(transactionId)) {
                return t;
            }
        }
        return null;
    }

}
