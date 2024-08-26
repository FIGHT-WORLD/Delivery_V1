package com.fight_world.mono.domain.report.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    REPORT_TITLE_VALID_MIN(HttpStatus.BAD_REQUEST, "제목은 최소 5자 이상 작성해 주셔야 합니다."),
    REPORT_CONTENT_VALID_MIN(HttpStatus.BAD_REQUEST, "내용은 최소 10자 이상 작성해 주셔야 합니다."),
    REPORT_TYPE_VALID(HttpStatus.BAD_REQUEST, "신고 유형을 선택해 주세요");

    private final HttpStatus httpStatus;
    private final String message;
}
