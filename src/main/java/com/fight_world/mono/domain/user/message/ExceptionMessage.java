package com.fight_world.mono.domain.user.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    USER_EMAIL_VALID(HttpStatus.BAD_REQUEST, "유저 이메일은 3글자 이상이어야 합니다."),
    SIGNUP_DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    SIGNUP_DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    SELECT_NOT_FOUND_USER(HttpStatus.NOT_FOUND, "조회 회원이 없습니다."),
    SELECT_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "조회 권한이 없습니다."),
    UPDATE_DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    UPDATE_DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    UPDATE_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "수정 권한이 없습니다."),
    DELETE_INVALID_AUTHORIZATION(HttpStatus.NOT_FOUND, "삭제 회원이 없습니다."),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
