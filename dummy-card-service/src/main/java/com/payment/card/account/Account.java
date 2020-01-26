package com.payment.card.account;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String accountNumber;
    private String cardPAN;
    private String cardHolderName;
    private String cvv;
    private String cardValidThru;
    private Double balance;

}
