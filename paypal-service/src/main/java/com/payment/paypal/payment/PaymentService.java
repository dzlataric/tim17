package com.payment.paypal.payment;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaymentService {

	Payment createPayment(PaymentRequest paymentRequest) throws PayPalRESTException;

	Payment executePayment(ExecutePayment executePayment) throws PayPalRESTException;

}
