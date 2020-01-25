package com.payment.card.client;

import java.util.List;

public interface IClientService {

    List<Client> findAllClients();
    void saveClient(Client client);
}
