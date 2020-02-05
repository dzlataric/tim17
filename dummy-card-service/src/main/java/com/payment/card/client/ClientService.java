package com.payment.card.client;

import com.payment.card.account.Account;
import com.payment.card.account.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Autowired
    public ClientService(final IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(c -> new Client(c.getId(), c.getName(), c.getAccounts()
                        .stream()
                        .map(a -> new Account(a.getAccountNumber(), a.getCardPAN(), a.getCardHolderName(),
                                a.getCvv(), a.getCardValidThru(), a.getBalance()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public void saveClient(Client client) {
        //TODO: impl. this better
        var c = new ClientEntity(client.getId(), client.getName(), null);
        var acc = client.getAccounts().stream().map(a -> new AccountEntity(a.getAccountNumber(), a.getCardPAN(),
                a.getCardHolderName(), a.getCvv(), a.getCardValidThru(), a.getBalance(), c))
                .collect(Collectors.toList());
        c.setAccounts(acc);
        clientRepository.save(c);
    }

    public void addToBalance(Double amount, Client client, String acc) {
        for (Account a : client.getAccounts()) {
            if (a.getAccountNumber().equals(acc) || a.getCardPAN().equals(acc)) {
                a.setBalance(a.getBalance() + amount);
            } else {
                log.error("Not existing client account!");
            }
        }
    }

    public boolean subtractFromBalance(Double amount, Client client, String acc) {
        for (Account a : client.getAccounts()) {
            if (a.getAccountNumber().equals(acc) || a.getCardPAN().equals(acc)) {
                if (a.getBalance() - amount >= 0) {
                    a.setBalance(a.getBalance() - amount);
                    return true;
                }
            } else {
                log.error("Not existing client account!");
            }
        }
        return false;
    }

    public Client findClientByPAN(String pan) {
        var clients = findAllClients();
        for (Client c : clients) {
            for (Account a : c.getAccounts()) {
                if (a.getCardPAN().equals(pan)) {
                    return c;
                }
            }
        }
        return null;
    }

    public Client findClientByAccountNumber(String accountNumber) {
        var clients = findAllClients();
        for (Client c : clients) {
            for (Account a : c.getAccounts()) {
                if (a.getAccountNumber().equals(accountNumber)) {
                    return c;
                }
            }
        }
        return null;
    }

    public Client findClientById(String id) {
        for (Client c : findAllClients()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

}
