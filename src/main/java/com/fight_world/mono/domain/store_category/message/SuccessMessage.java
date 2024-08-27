package com.fight_world.mono.domain.store_category.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_GET_STORE_CATEGORY_LIST(HttpStatus.OK, "가게 카테고리 조회가 완료되었습니다"),
    SUCCESS_CHANGE_STORE_CATEGORY(HttpStatus.OK, "가게 카테고리 수정이 완료되었습니다"),
    SUCCESS_ADD_STORE_CATEGORY(HttpStatus.CREATED, "가게 카테고리 추가가 완료되었습니다"),
    SUCCESS_DELETE_STORE_CATEGORY(HttpStatus.OK,"가게 카테고리 삭제가 완료되었습니다");


    private final HttpStatus httpStatus;
    private final String message;
}
