package com.fight_world.mono.domain.deliveryArea.exception;

import com.fight_world.mono.domain.menu.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class DeliveryAreaException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public DeliveryAreaException(ExceptionMessage exceptionMessage) {
        super("[DeliveryArea Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {

        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {

        return exceptionMessage.getMessage();
    }
}
