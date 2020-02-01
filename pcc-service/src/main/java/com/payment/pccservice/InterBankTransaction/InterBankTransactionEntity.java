package com.payment.pccservice.InterBankTransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "INTER_BANK_TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
public class InterBankTransactionEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

}
