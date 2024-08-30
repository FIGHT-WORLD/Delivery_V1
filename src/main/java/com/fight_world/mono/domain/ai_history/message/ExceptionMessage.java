package com.fight_world.mono.domain.ai_history.message;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    GUARD(FORBIDDEN, "AI 작성 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
