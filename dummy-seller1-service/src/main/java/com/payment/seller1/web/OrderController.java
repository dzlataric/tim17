package com.payment.seller1.web;

import com.payment.seller1.order.Order;
import com.payment.seller1.order.OrderConfiguration;
import com.payment.seller1.order.PlaceOrderRequest;
import com.payment.seller1.order.PlaceOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class OrderController {

    private final OrderConfiguration orderConfiguration;

    @Autowired
    public OrderController(final OrderConfiguration orderConfiguration) {
        this.orderConfiguration = orderConfiguration;
    }

    @RequestMapping(value = "/order/place", method = RequestMethod.POST)
    public ResponseEntity<PlaceOrderResponse> placeNewOrderService(@RequestBody final PlaceOrderRequest request) {

        Float orderPrice = 200f; // forTesting
        String newOrderId = String.valueOf(new Random().nextInt());

        log.info("Received request for new order placement, orderId: {}", newOrderId);
        Order newOrder = new Order(newOrderId, request.getMagazineId(), request.getUserId(), orderPrice);

        // TODO: handle transaction and payment
        // send transaction details to KP
        // redirect user to payment page
        // after successful payment, attach ACL to users for granting access to magazine

        orderConfiguration.getOrders().put(newOrderId, newOrder);

        return ResponseEntity.status(HttpStatus.OK).body(PlaceOrderResponse.builder().id(newOrderId).magazineId(request.getMagazineId())
                .userId(request.getUserId()).price(orderPrice)
                .count(orderConfiguration.getOrders().size()).build());
    }

}
