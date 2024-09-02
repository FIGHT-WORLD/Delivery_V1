package com.fight_world.mono.domain.order.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
    ORDER_USER_VALID(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    ORDER_CANT_CHANGE_STATUS(HttpStatus.FORBIDDEN, "해당 상태로 변경 할 수 없는 상태입니다."),
    CANNOT_DELETE_AFTER_TIMEOUT(HttpStatus.BAD_REQUEST, "5분이 지난 주문은 삭제할 수 없습니다."),
    ORDER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "주문은 자신의 주문 상태만 변경할 수 있습니다."),

    ORDER_MENU_CNT_MIN(HttpStatus.BAD_REQUEST, "메뉴 주문은 최소 1개 이상만 등록할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
