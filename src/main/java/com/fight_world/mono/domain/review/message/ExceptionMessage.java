package com.fight_world.mono.domain.review.message;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    REVIEW_CONTENT_VALID_MIN(BAD_REQUEST, "리뷰는 5글자 이상 작성해주셔야 합니다."),
    REVIEW_STAR_VALID(BAD_REQUEST, "리뷰 별점은 1점 ~ 5점까지 입력하실 수 있습니다."),
    GUARD(FORBIDDEN, "권한이 없습니다."),
    ALREADY_HAS_REVIEW(CONFLICT, "이미 작성한 리뷰가 있습니다."),
    NOT_FOUND_REVIEW(NOT_FOUND, "리뷰를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
