package com.fight_world.mono.domain.order.dto.request;

public record OrderMenuUpdateRequestDto(
        String menu_id,
        Integer cnt
) {

}
