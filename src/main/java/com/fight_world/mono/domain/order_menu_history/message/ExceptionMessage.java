package com.fight_world.mono.domain.order_menu_history.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    ALREADY_PAY_IT(HttpStatus.CONFLICT, "이미 결제 된 주문 건 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
