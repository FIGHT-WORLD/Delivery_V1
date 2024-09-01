package com.fight_world.mono.domain.user_address.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    FORBIDDEN_USER_AUTHORITY(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    NOT_FOUND_DELETED_USER(HttpStatus.NOT_FOUND, "삭제된 유저입니다."),
    NOT_FOUND_USER_ADDRESS(HttpStatus.NOT_FOUND, "해당 주소를 찾을 수 없습니다."),
    INVALID_USER_AUTHORIZATION(HttpStatus.CONFLICT, "작성자만 수정할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
