package com.fight_world.mono.domain.payment.exception;

import com.fight_world.mono.domain.payment.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class PaymentException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public PaymentException(ExceptionMessage exceptionMessage) {
        super("[Payment Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
