package com.payment.card.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

interface ITransactionRepository extends JpaRepository<TransactionEntity, BigInteger> {
}
