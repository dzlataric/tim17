package com.payment.paypal.seller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface SellerRepository extends JpaRepository<SellerEntity, Long> {

	Optional<SellerEntity> findByEmail(String email);

}
