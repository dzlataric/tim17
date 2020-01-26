package com.payment.concentrator.merchant;

import com.payment.commons.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "MERCHANT")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantEntitiy {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "BANK_ID", nullable = false)
    private String bankId;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
//    @Enumerated(EnumType.STRING)
//    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
//    private List<PaymentType> paymentTypes;

}
