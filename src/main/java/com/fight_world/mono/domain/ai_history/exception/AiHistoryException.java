package com.fight_world.mono.domain.ai_history.exception;

import com.fight_world.mono.domain.ai_history.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class AiHistoryException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public AiHistoryException(ExceptionMessage exceptionMessage) {
        super("[AiHistory Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {

        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {

        return exceptionMessage.getMessage();
    }
}
