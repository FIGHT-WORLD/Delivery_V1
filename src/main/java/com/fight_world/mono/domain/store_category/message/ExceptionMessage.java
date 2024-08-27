package com.fight_world.mono.domain.store_category.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    STORE_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리를 찾을 수 없습니다."),
    STORE_CATEGORY_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 존재하는 카테고리 이름입니다."),
    STORE_CATEGORY_NOT_VALID(HttpStatus.CONFLICT, "카테고리 이름은 필수 입력값입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
