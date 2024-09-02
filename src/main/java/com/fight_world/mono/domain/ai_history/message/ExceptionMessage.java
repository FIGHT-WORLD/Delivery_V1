package com.fight_world.mono.domain.ai_history.message;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    GUARD(FORBIDDEN, "AI 작성 권한이 없습니다."),
    NOTFUND(NOT_FOUND, "조회한 AI 요청 ID가 없습니다."),
    OWNER(FORBIDDEN, "조회 권한이 없습니다."),
    DELETE(FORBIDDEN, "삭제 권한이 없습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
