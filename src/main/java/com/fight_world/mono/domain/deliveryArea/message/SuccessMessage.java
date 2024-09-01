package com.fight_world.mono.domain.deliveryArea.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_ADD_DELIVERY_AREA(HttpStatus.CREATED, "배달 가능 지역 등록이 완료되었습니다."),
    SUCCESS_MODIFY_DELIVERY_AREA(HttpStatus.OK, "배달 가능 지역 수정이 완료되었습니다."),
    SUCCESS_DELETE_DELIVERY_AREA(HttpStatus.OK, "배달 가능 지역 삭제가 완료되었습니다."),
    SUCCESS_GET_DELIVERY_AREA(HttpStatus.OK, "배달 가능 지역 조회가 완료되었습니다."),
    SUCCESS_GET_DELIVERY_AVAILABLE_STORES(HttpStatus.OK, "배달 가능 가게 조회가 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
