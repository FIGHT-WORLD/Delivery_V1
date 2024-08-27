package com.fight_world.mono.domain.payment.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    KAKAO_PAY("카카오 페이");

    private final String type;
}
