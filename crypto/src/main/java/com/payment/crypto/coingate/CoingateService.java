package com.payment.crypto.coingate;

public interface CoingateService {

	CoingateOrderResponse createOrder(CoingateOrderRequest request);

}
