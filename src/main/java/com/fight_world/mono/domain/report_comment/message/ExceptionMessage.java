package com.fight_world.mono.domain.report_comment.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    COMMENT_CREATE(HttpStatus.UNAUTHORIZED, "답변을 작성할 권한이 없습니다."),
    COMMENT_UPDATE(HttpStatus.UNAUTHORIZED, "답변을 수정할 권한이 없습니다."),
    COMMENT_READ(HttpStatus.UNAUTHORIZED, "답변을 조회할 권한이 없습니다."),
    COMMENT_DELETE(HttpStatus.UNAUTHORIZED, "답변을 삭제할 권한이 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 답변을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
