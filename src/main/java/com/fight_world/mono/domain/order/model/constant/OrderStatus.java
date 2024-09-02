package com.fight_world.mono.domain.order.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    CART("장바구니 상태"),

    CHECKING("가게 확인 중"),

    COOKING("조리 중"),

    DELIVERY("배달 중"),

    DONE("배달 완료");

    private final String status;
}
