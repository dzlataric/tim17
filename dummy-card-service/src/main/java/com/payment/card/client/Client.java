package com.payment.card.client;

import com.payment.card.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    //TODO: switch id to UUID (if this is UUID insert from data.sql doesn't work)
    private String id;
    private String name;
    private List<Account> accounts;

}
