package com.payment.concentrator.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String merchantId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM HH:mm:ss")
    private LocalDateTime merchantTimestamp;
    private Double amount;
    private String currency;
    private String paymentUrl;
    private String status;

}
