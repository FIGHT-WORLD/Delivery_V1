package com.fight_world.mono.domain.review.exception;

import com.fight_world.mono.domain.review.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class ReviewException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public ReviewException(ExceptionMessage exceptionMessage) {
        super("[Review Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
