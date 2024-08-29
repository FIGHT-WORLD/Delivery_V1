package com.fight_world.mono.domain.payment.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATED_PAYMENT(HttpStatus.CREATED, "결제가 완료되었습니다."),
    GET_PAYMENTS(HttpStatus.OK, "결제 목록 조회가 완료되었습니다."),
    GET_PAYMENT(HttpStatus.OK, "결제 조회가 완료되었습니다."),
    DELETE_PAYMENT(HttpStatus.OK, "결제 삭제가 완료되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
