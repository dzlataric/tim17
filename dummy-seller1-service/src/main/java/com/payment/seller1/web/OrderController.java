package com.payment.seller1.web;

import com.payment.seller1.magazine.Magazine;
import com.payment.seller1.magazine.MagazineConfiguration;
import com.payment.seller1.order.Order;
import com.payment.seller1.order.OrderConfiguration;
import com.payment.seller1.order.PlaceOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;

@Slf4j
@Controller
public class OrderController {

    @Value("${registration.id}")
    private String id;

    private final OrderConfiguration orderConfiguration;
    private final MagazineConfiguration magazineConfiguration;

    @Autowired
    public OrderController(final OrderConfiguration orderConfiguration, final MagazineConfiguration magazineConfiguration) {
        this.orderConfiguration = orderConfiguration;
        this.magazineConfiguration = magazineConfiguration;
    }

    @RequestMapping(value = "/order/place", method = RequestMethod.POST)
    public RedirectView placeNewOrderService(@ModelAttribute PlaceOrderRequest request, RedirectAttributesModelMap model) {

        String newOrderId = String.valueOf(new Random().nextInt());

        log.info("Received request for new order placement, orderId: {}", newOrderId);
        Magazine magazine = magazineConfiguration.getMagazines().get(request.getMagazineId());
        Order newOrder = new Order(newOrderId, request.getUserId(), magazine);

        orderConfiguration.getOrders().put(newOrderId, newOrder);

        // TODO: wrap in request, don't send parameters
        model.addAttribute("userId", request.getUserId());
        model.addAttribute("merchantId", id);
        model.addAttribute("subscriptionFee", magazine.getSubscriptionFee());
        return new RedirectView("https://localhost:8081/payment");
    }

}
