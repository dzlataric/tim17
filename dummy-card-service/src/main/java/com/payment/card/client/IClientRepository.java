package com.payment.card.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface IClientRepository extends JpaRepository<ClientEntity, BigInteger> {
}
