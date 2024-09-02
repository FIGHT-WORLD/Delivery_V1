package com.fight_world.mono.domain.deliveryArea.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    MENU_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "배달 가능 지역 수정 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
