package com.payment.concentrator.merchant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface IMerchantRepository extends JpaRepository<MerchantEntitiy, BigInteger> {
}
