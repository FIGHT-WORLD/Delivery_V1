package com.fight_world.mono.domain.report.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SuccessMessage {
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
