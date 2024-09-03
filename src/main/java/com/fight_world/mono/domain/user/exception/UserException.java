package com.fight_world.mono.domain.user.exception;

import com.fight_world.mono.domain.user.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public UserException(ExceptionMessage exceptionMessage) {
        super("[User Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
