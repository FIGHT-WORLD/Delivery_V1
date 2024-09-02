package com.fight_world.mono.domain.order.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATED_ORDER(HttpStatus.CREATED, "주문 등록이 완료되었습니다."),
    UPDATED_ORDER(HttpStatus.OK, "주문 수정이 완료되었습니다."),
    GET_ORDERS(HttpStatus.OK, "주문 목록 조회가 완료되었습니다."),
    GET_ORDER(HttpStatus.OK, "주문 조회가 완료되었습니다."),
    DELETE_ORDER(HttpStatus.OK, "주문 삭제가 완료되었습니다."),
    UPDATE_ORDER_TO_COOKING(HttpStatus.OK, "주문 정보가 조리중으로 수정 되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
