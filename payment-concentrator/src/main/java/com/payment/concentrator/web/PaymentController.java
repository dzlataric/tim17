package com.payment.concentrator.web;

import com.payment.commons.PaymentType;
import com.payment.concentrator.payment.Payment;
import com.payment.concentrator.payment.PaymentTypeConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
public class PaymentController {

    private final PaymentTypeConfiguration paymentTypeConfiguration;

    @Autowired
    public PaymentController(final PaymentTypeConfiguration paymentTypeConfiguration) {
        this.paymentTypeConfiguration = paymentTypeConfiguration;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String paymentView(Model model) {
        log.info("Choose magazine to order.");
        model.addAttribute("request", new Payment());
        model.addAttribute("paymentTypes", paymentTypeConfiguration.getPaymentTypes());
        return "payment";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public RedirectView redirect(@ModelAttribute Payment request) {

        // TODO: get payment endpoints that merchant registered for
        if (request.getPaymentType().equals(PaymentType.CARD)) {
            return new RedirectView("https://localhost:8080/cardPayment");
        }

        return new RedirectView("error");
    }

}
