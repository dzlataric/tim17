package com.payment.concentrator.bank;

import java.util.List;

public interface IBankService {

    List<Bank> findAllBanks();
    Bank findBankById(String id);

}
