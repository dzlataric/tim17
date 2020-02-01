package com.payment.paypal.seller;

public interface SellerService {

	Seller registerNewSeller(RegistrationRequest registrationRequest);

	SellerEntity getByEmail(String email);

	Long getIdByEmail(String email);

}
