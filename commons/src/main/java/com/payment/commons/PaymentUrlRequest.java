package com.payment.commons;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUrlRequest {

    private String merchantId;
    private String merchantPassword;
    private Double amount;
    private Integer merchantOrderID;
    private LocalDateTime merchantTimestamp;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
