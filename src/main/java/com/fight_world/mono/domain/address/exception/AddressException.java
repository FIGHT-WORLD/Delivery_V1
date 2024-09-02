package com.fight_world.mono.domain.address.exception;

import com.fight_world.mono.domain.address.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class AddressException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public AddressException(ExceptionMessage exceptionMessage) {
        super("[Address Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {

        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {

        return exceptionMessage.getMessage();
    }
}
