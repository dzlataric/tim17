package com.payment.card.transaction;

import com.payment.commons.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @Column(name = "ID")
    private UUID transactionId;
    @Column(name = "MERCHANT_ID", nullable = false)
    private UUID merchantId;
    @Column(name = "MERCHANT_ORDER_ID", nullable = false)
    private UUID merchantOrderId;
    @Column(name = "MERCHANT_TIMESTAMP", nullable = false)
    private String merchantTimestamp; // TODO: datetime
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY", nullable = false)
    private Currency currency;
    @Column(name = "SUCCESS_URL", nullable = false)
    private String successUrl;
    @Column(name = "FAILED_URL", nullable = false)
    private String failedUrl;
    @Column(name = "ERROR_URL", nullable = false)
    private String errorUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private TransactionStatus status;

}
