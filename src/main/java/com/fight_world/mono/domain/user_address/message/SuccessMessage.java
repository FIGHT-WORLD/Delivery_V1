package com.fight_world.mono.domain.user_address.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_SUCCESS_USER_ADDRESS(HttpStatus.CREATED, "주소 등록 성공"),
    GET_SUCCESS_USER_ADDRESS_LIST(HttpStatus.CREATED, "주소 전체 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
