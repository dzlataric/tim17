package com.payment.pccservice.bank;

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
@Table(name = "BANK")
@AllArgsConstructor
@NoArgsConstructor
public class BankEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PAYMENT_URL", nullable = false)
    private String paymentUrl;
    @Column(name = "IIN", nullable = false)
    private String IIN;

}
