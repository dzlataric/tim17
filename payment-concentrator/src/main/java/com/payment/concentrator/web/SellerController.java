package com.payment.concentrator.web;

import com.payment.concentrator.payment.PaymentType;
import com.payment.concentrator.seller.Seller;
import com.payment.concentrator.seller.SellerConfiguration;
import com.payment.concentrator.seller.SellerRegistrationRequest;
import com.payment.concentrator.seller.SellerRegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class SellerController {

    private final SellerConfiguration sellerConfiguration;

    @Autowired
    public SellerController(final SellerConfiguration sellerConfiguration) {
        this.sellerConfiguration = sellerConfiguration;
    }

    @RequestMapping(value = "/sellers", method = RequestMethod.GET)
    public Map<String, Seller> getSellersConfiguration() {
        log.info("Getting sellers...");
        return sellerConfiguration.getSellers();
    }

    @RequestMapping(value = "/seller/register", method = RequestMethod.POST)
    public ResponseEntity<SellerRegistrationResponse> registerNewSellerService(@RequestBody final SellerRegistrationRequest request) {
        log.info("Received request for new seller service with id: {}", request.getId());
        Seller newSeller = new Seller(request.getId(), request.getName(), request.getPaymentTypes());
        sellerConfiguration.getSellers().put(request.getId(), newSeller);
        return ResponseEntity.status(HttpStatus.OK).body(SellerRegistrationResponse.builder().id(request.getId()).name(request.getName()).paymentTypes(request.getPaymentTypes())
                .count(sellerConfiguration.getSellers().size()).build());
    }
}
