package com.payment.concentrator.merchant;

import com.payment.commons.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class MerchantService implements IMerchantService {

    private final IMerchantRepository merchantRepository;

    @Autowired
    public MerchantService(final IMerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public List<Merchant> findAllMerchants() {
        return merchantRepository.findAll()
                .stream()
                .map(m -> new Merchant(m.getId(), m.getBankId(), m.getName(), m.getPassword(),
                        m.getPaymentTypes()))
                .collect(Collectors.toList());
    }

    @Override
    public Merchant getMerchantById(String id) {
        for (Merchant m : findAllMerchants()) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public void saveMerchant(Merchant m) {
        merchantRepository.save(new MerchantEntitiy(m.getId(), m.getBankId(),
                m.getName(), m.getPassword(), m.getPaymentTypes()));
    }

}
