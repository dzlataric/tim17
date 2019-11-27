package com.payment.seller1.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Random;

@Slf4j
@Component
@ControllerAdvice
public class Seller1ControllerAdvice {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ErrorResponse> internalServerError(final RuntimeException exception) {
        final var errorMessage = exception.getMessage() != null ? exception.getMessage() : exception.getClass().getSimpleName();
        log.error(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .code(new Random().nextInt())
                .message(errorMessage)
                .build());
    }

}
