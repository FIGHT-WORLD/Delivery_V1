package com.fight_world.mono.domain.order_menu.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    ORDER_MENU_CNT_MIN(HttpStatus.BAD_REQUEST, "메뉴 선택 수량은 1개 이상이어야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
