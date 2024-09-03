package com.fight_world.mono.domain.menu.exception;

import com.fight_world.mono.domain.menu.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class MenuException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public MenuException(ExceptionMessage exceptionMessage) {
        super("[Menu Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {

        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {

        return exceptionMessage.getMessage();
    }
}
