package com.fight_world.mono.domain.report_comment.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_COMMENT(HttpStatus.CREATED, "신고 피드백 작성이 완료되었습니다."),
    UPDATE_COMMENT(HttpStatus.OK, "신고 피드백 수정이 완료되었습니다."),
    GET_COMMENT(HttpStatus.OK, "신고 피드백 조회가 완료되었습니다."),
    DELETE_COMMENT(HttpStatus.OK, "신고 피드백 삭제가 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
