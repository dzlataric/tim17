package com.payment.card.account;

import com.payment.card.client.ClientEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate cardValidThru;
    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

}
