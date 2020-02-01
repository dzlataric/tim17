package com.payment.paypal.seller;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class SellerServiceImpl implements SellerService {

	private final SellerRepository sellerRepository;

	@Override
	public Seller registerNewSeller(final RegistrationRequest registrationRequest) {
		final var existingSeller = sellerRepository.findByEmail(registrationRequest.getEmail());
		if (existingSeller.isPresent()) {
			throw new EntityExistsException(
				String.format("Seller with email %s already exists", registrationRequest.getEmail()));
		}
		return mapSeller(sellerRepository.save(SellerEntity.builder()
			.email(registrationRequest.getEmail())
			.merchantId(registrationRequest.getMerchantId())
			.allowsSubscription(registrationRequest.getAllowsSubscription())
			.build()));
	}

	@Override
	public SellerEntity getByEmail(final String email) {
		return sellerRepository.findAll().stream().filter(se -> email.equals(se.getEmail())).findFirst().orElseThrow();
	}

	@Override
	public Long getIdByEmail(final String email) {
		return sellerRepository.findByEmail(email).orElseThrow().getId();
	}

	private Seller mapSeller(final SellerEntity seller) {
		return Seller.builder()
			.id(seller.getId())
			.email(seller.getEmail())
			.merchantId(seller.getMerchantId())
			.allowsSubscription(seller.getAllowsSubscription())
			.build();
	}

}
