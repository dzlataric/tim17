package com.payment.seller1.web;

import com.payment.seller1.magazine.MagazineConfiguration;
import com.payment.seller1.order.PlaceOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class OrderViewController {

    private final MagazineConfiguration magazineConfiguration;

    @Autowired
    public OrderViewController(final MagazineConfiguration magazineConfiguration) {
        this.magazineConfiguration = magazineConfiguration;
    }

    @RequestMapping( value = "/order", method = RequestMethod.GET)
    public String test(Model model) {
        log.info("Choose magazine to order.");
        model.addAttribute("request", new PlaceOrderRequest());
        model.addAttribute("magazines", magazineConfiguration.getMagazines());
        return "orderView";
    }

}
