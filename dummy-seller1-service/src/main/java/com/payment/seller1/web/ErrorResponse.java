package com.payment.seller1.web;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
class ErrorResponse {

    private final int code;
    private final String message;

}
