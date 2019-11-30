package com.payment.card.web;

import com.payment.card.payment.CardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class CardViewController {

    @RequestMapping(value = "/cardPayment", method = RequestMethod.GET)
    public String cardView(Model model){
        model.addAttribute("request", new CardInfo());
        return "cardPayment";
    }
}
