package com.payment.concentrator.merchant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class MerchantService implements IMerchantService {

    @Override
    public List<Merchant> findAllMerchants() {
        // TODO: implement
        return null;
    }

    @Override
    public Merchant getMerchantById(String id) {
        // TODO: implement
        return null;
    }

    @Override
    public void saveMerchant(Merchant merchant) {
        // TODO: implement
    }

}
