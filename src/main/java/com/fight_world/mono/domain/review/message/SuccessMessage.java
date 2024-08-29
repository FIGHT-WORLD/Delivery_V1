package com.fight_world.mono.domain.review.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATED_REVIEW(HttpStatus.CREATED, "리뷰 등록이 완료되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
