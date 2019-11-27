package com.payment.seller1.web;

import com.payment.seller1.magazine.Magazine;
import com.payment.seller1.magazine.MagazineConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MagazineController {

    private final MagazineConfiguration magazineConfiguration;

    @Autowired
    public MagazineController(final MagazineConfiguration magazineConfiguration) {
        this.magazineConfiguration = magazineConfiguration;
    }

    @RequestMapping(value = "/magazines", method = RequestMethod.GET)
    public Map<String, Magazine> getMagazineConfiguration() {
        log.info("Getting magazines...");
        return magazineConfiguration.getMagazines();
    }

}
