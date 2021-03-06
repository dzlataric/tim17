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
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
class PaymentServiceImp implements PaymentService {

	private final APIContext paypalApiContext;

	@Override
	public PaymentResponse createPayment(final PaymentRequest paymentRequest) throws PayPalRESTException {
		final String accessToken = paypalApiContext.fetchAccessToken();
		log.info(accessToken);
		Payment payment = new Payment();
		payment.setIntent(paymentRequest.getIntent().name());
		payment.setPayer(preparePayer(paymentRequest));
		payment.setTransactions(prepareTransaction(paymentRequest));
		payment.setRedirectUrls(prepareRedirectUrls(paymentRequest));
		final var response = payment.create(paypalApiContext);
		return PaymentResponse.builder().id(response.getId()).state(response.getState())
			.links(response.getLinks().stream().map(l -> PaymentLink.builder().type(l.getRel()).url(l.getHref()).build()).collect(
				Collectors.toList()))
			.build();
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

	private RedirectUrls prepareRedirectUrls(final PaymentRequest paymentRequest) {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(paymentRequest.getFailedUrl());
		redirectUrls.setReturnUrl(paymentRequest.getSuccessUrl());
		return redirectUrls;
	}

	private List<Transaction> prepareTransaction(final PaymentRequest paymentRequest) {
		Transaction transaction = new Transaction();
		transaction.setDescription(paymentRequest.getDescription());
		transaction.setAmount(new Amount(paymentRequest.getCurrency().name(), String.format("%.2f", paymentRequest.getAmount())));
		return List.of(transaction);
	}
}
