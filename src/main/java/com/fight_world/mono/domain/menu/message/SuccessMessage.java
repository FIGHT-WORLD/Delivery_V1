package com.fight_world.mono.domain.menu.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    ;
    private final HttpStatus httpStatus;
    private final String message;
}
