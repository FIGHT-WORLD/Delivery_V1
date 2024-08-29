package com.fight_world.mono.domain.menu.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    MENU_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 메뉴를 찾을 수 없습니다."),
    MENU_DESCRIPTION_VALID(HttpStatus.BAD_REQUEST, "메뉴 설명은 50자는 최소 1 ~ 50글자 작성해야 합니다."),
    MENU_PRICE_VALID(HttpStatus.BAD_REQUEST, "메뉴 가격은 음수가 될 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
