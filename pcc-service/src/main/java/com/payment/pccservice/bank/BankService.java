package com.payment.pccservice.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class BankService implements IBankService {

    private final IBankRepository bankRepository;

    @Autowired
    public BankService(final IBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> findAllBanks() {
        return bankRepository.findAll()
                .stream()
                .map(b -> new Bank(b.getId(), b.getName(), b.getPaymentUrl(), b.getIIN()))
                .collect(Collectors.toList());
    }

    @Override
    public Bank findBankById(String id) {
        for (Bank b : findAllBanks()) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public Bank findBankByIIN(String iin) {
        for (Bank b : findAllBanks()) {
            if (b.getIin().equals(iin)) {
                return b;
            }
        }
        return null;
    }

}
