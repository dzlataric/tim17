package com.payment.card.account;

import com.payment.card.client.ClientEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;
    @Column(name = "CARDPAN", nullable = false)
    private String cardPAN;
    @Column(name = "CHN", nullable = false)
    private String cardHolderName;
    @Column(name = "CVV", nullable = false)
    private String cvv;
    @Column(name = "CARDVALIDTHRU", nullable = false)
    private String cardValidThru;
    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

}
