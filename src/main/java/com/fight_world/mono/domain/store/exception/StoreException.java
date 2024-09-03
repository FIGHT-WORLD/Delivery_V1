package com.fight_world.mono.domain.store.exception;

import com.fight_world.mono.domain.store.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class StoreException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public StoreException(ExceptionMessage exceptionMessage) {
        super("[Store Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
