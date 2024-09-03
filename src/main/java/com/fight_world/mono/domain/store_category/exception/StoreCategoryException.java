package com.fight_world.mono.domain.store_category.exception;

import com.fight_world.mono.domain.store_category.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class StoreCategoryException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public StoreCategoryException(ExceptionMessage exceptionMessage) {
        super("[Store Category Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
