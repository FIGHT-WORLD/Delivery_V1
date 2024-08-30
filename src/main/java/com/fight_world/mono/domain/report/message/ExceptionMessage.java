package com.fight_world.mono.domain.report.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    REPORT_TITLE_VALID_MIN(HttpStatus.BAD_REQUEST, "제목은 최소 5자 이상 작성해 주셔야 합니다."),
    REPORT_CONTENT_VALID_MIN(HttpStatus.BAD_REQUEST, "내용은 최소 10자 이상 작성해 주셔야 합니다."),
    YOUR_NOT_REPORTER(HttpStatus.FORBIDDEN, "작성하신 신고만 확인할 수 있습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "스토어가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
