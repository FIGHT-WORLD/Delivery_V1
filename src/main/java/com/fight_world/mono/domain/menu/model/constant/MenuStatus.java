package com.fight_world.mono.domain.menu.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuStatus {

    AVAILABLE("AVAILABLE"),
    SOLDOUT("SOLDOUT");

    private final String status;
}
