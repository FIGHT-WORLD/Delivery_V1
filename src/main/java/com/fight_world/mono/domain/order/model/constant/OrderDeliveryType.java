package com.fight_world.mono.domain.order.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderDeliveryType {

    DELIVERY("배달"),

    PACK("포장");

    private final String type;
}
