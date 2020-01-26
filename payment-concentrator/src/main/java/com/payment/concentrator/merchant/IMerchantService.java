package com.payment.concentrator.merchant;

import java.util.List;

public interface IMerchantService {

    List<Merchant> findAllMerchants();
    Merchant getMerchantById(String id);
    void saveMerchant(Merchant merchant);

}
