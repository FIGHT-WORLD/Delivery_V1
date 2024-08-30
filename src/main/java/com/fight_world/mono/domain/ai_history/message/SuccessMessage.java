package com.fight_world.mono.domain.ai_history.message;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATED_AI_PRODUCT_DESCRIPTION(CREATED, "상품 설명 자동 생성 요청이 완료되었습니다.");

    private final HttpStatus status;
    private final String message;
}
