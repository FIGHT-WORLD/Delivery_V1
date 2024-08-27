package com.fight_world.mono.domain.store.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_REGISTER_STORE(HttpStatus.CREATED, "가게 등록이 완료되었습니다."),
    SUCCESS_MODIFY_STORE(HttpStatus.OK, "가게 수정이 완료되었습니다."),
    SUCCESS_DELETE_STORE(HttpStatus.OK, "가게 삭제가 완료되었습니다."),
    SUCCESS_GET_ONE_STORE(HttpStatus.OK, "가게 조회가 완료되었습니다."),
    SUCCESS_GET_STORE_LIST(HttpStatus.OK, "가게 목록 조회가 완료되었습니다."),
    SUCCESS_SEARCH_STORE(HttpStatus.OK, "가게 조회가 완료되었습니다."),
    SUCCESS_CHANGE_STORE_STATUS(HttpStatus.OK, "가게 조회가 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}