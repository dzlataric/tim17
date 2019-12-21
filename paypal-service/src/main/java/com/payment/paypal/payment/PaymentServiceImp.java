package com.payment.paypal.payment;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
class PaymentServiceImp implements PaymentService {

	private final APIContext paypalApiContext;

	@Autowired
	public PaymentServiceImp(final APIContext paypalApiContext) {
		this.paypalApiContext = paypalApiContext;
	}

	@Override
	public Payment createPayment(final PaymentRequest paymentRequest) throws PayPalRESTException {
		Payment payment = new Payment();
		payment.setIntent(paymentRequest.getIntent().name());
		payment.setPayer(preparePayer(paymentRequest));
		payment.setTransactions(prepareTransaction(paymentRequest));
		payment.setRedirectUrls(prepareRedirectUrls());
		return payment.create(paypalApiContext);
	}

	@Override
	public Payment executePayment(final ExecutePayment executePayment) throws PayPalRESTException {
		Payment payment = new Payment();
		payment.setId(executePayment.getPaymentId());
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(executePayment.getPayerId());
		return payment.execute(paypalApiContext, paymentExecute);
	}

	private Payer preparePayer(final PaymentRequest paymentRequest) {
		Payer payer = new Payer();
		payer.setPaymentMethod(paymentRequest.getPaymentMethod().name());
		return payer;
	}

	private RedirectUrls prepareRedirectUrls() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/paypal/create/cancel");
		redirectUrls.setReturnUrl("http://localhost:8080/paypal/create/success");
		return redirectUrls;
	}

	private List<Transaction> prepareTransaction(final PaymentRequest paymentRequest) {
		Transaction transaction = new Transaction();
		transaction.setDescription(paymentRequest.getDescription());
		transaction.setAmount(new Amount(paymentRequest.getCurrency().name(), String.format("%.2f", paymentRequest.getAmount())));
		return List.of(transaction);
	}
}
