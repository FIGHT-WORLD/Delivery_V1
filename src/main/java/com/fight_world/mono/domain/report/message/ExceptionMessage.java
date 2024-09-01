package com.fight_world.mono.domain.report.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    YOUR_NOT_REPORTER(HttpStatus.UNAUTHORIZED, "작성하신 신고만 확인할 수 있습니다."),
    REPORT_ADMIN(HttpStatus.UNAUTHORIZED, "관리자만 조회할 수 있습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "스토어가 없습니다."),
    KEYWORD_NOT_PROVIDED(HttpStatus.NOT_FOUND, "검색한 키워드가 존재하지 않습니다."),
    KEYWORD_EMPTY(HttpStatus.BAD_REQUEST, "키워드는 최소 1자 이상 작성해 주셔야 합니다."),
    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "조회한 report가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
