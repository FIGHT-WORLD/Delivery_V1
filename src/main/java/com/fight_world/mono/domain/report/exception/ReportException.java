package com.fight_world.mono.domain.report.exception;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

import com.fight_world.mono.domain.report.message.ExceptionMessage;

public class ReportException extends RuntimeException {

    public ReportException(ExceptionMessage exceptionMessage) {
        super(String.valueOf(message));
    }
}
