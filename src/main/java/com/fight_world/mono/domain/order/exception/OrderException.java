package com.fight_world.mono.domain.order.exception;

import com.fight_world.mono.domain.order.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class OrderException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public OrderException(ExceptionMessage exceptionMessage) {
        super("[Order Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
