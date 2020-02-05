package com.payment.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String transactionId;
    private String merchantId;
    private String merchantPassword;
    private String merchantOrderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM HH:mm:ss")
    private LocalDateTime merchantTimestamp;
    private Double amount;
    private String currency;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
