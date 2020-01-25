package com.payment.card.client;

import com.payment.card.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "CLIENT")
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {


    @Id
    @Column(name = "ID", nullable = false)
    private String id; //TODO: switch id to UUID (if this is UUID insert from data.sql doesn't work)

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

}
