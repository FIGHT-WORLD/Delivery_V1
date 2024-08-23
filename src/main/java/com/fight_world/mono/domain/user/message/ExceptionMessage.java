package com.fight_world.mono.domain.user.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    USER_EMAIL_VALID(HttpStatus.BAD_REQUEST, "유저 이메일은 3글자 이상이어야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
