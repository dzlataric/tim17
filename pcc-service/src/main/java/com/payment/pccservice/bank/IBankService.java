package com.payment.pccservice.bank;

import java.util.List;

public interface IBankService {

    List<Bank> findAllBanks();
    Bank findBankById(String id);
    Bank findBankByIIN(String iin);

}
