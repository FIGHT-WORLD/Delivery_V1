package com.fight_world.mono.domain.payment.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    PAYMENT_NEED_BIGGER_THAN_ZERO(HttpStatus.BAD_REQUEST, "결제 가격은 음수가 될 수 없습니다."),
    NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, "결제 내역을 찾을 수 없습니다."),
    GUARD(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
