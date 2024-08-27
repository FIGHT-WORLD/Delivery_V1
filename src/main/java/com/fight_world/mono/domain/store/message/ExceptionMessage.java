package com.fight_world.mono.domain.store.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    STORE_PHONE_NUMBER_VALID(HttpStatus.BAD_REQUEST, "가게 전화번호는 필수 입력값입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
