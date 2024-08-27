package com.fight_world.mono.domain.order_menu.exception;

import com.fight_world.mono.domain.order_menu.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class OrderMenuException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public OrderMenuException(ExceptionMessage exceptionMessage) {
        super("[OrderMenu Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
