package com.fight_world.mono.domain.order_menu_history.exception;

import com.fight_world.mono.domain.payment.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class OrderMenuHistoryException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public OrderMenuHistoryException(ExceptionMessage exceptionMessage) {
        super("[OrderMenuHistory Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
