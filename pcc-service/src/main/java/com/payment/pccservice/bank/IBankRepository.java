package com.payment.pccservice.bank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface IBankRepository extends JpaRepository<BankEntity, BigInteger> {
}
