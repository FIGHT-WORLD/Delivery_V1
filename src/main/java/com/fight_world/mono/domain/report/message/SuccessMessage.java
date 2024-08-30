package com.fight_world.mono.domain.report.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATED_REPORT(HttpStatus.CREATED, "신고 작성이 완료되었습니다."),
    GET_REPORT_DETAIL(HttpStatus.OK, "신고 상세 조회가 완료되었습니다."),
    GET_REPORT(HttpStatus.OK, "신고 조회가 완료되었습니다."),
    UPDATE_REPORT(HttpStatus.OK, "신고 수정이 완료되었습니다."),
    DELETE_REPORT(HttpStatus.OK, "신고 삭제가 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
