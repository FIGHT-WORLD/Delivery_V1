package com.fight_world.mono.domain.report.exception;

import com.fight_world.mono.domain.report.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class ReportException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public ReportException(ExceptionMessage exceptionMessage) {
        super("[Report Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}