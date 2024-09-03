package com.fight_world.mono.domain.report_comment.exception;

import com.fight_world.mono.domain.report_comment.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class ReportCommentException extends RuntimeException {
    private final ExceptionMessage exceptionMessage;


    public ReportCommentException(ExceptionMessage exceptionMessage) {
        super("[ReportComment Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
