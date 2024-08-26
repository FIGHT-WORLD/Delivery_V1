package com.fight_world.mono.domain.store.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreStatus {

    OPEN("OPEN"),
    CLOSED("CLOSED");

    private final String status;
}
