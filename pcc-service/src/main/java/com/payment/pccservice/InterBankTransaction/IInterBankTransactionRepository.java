package com.payment.pccservice.InterBankTransaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface IInterBankTransactionRepository extends JpaRepository<InterBankTransactionEntity, BigInteger> {
}
