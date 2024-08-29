package com.fight_world.mono.domain.menu.dto.response;

import com.fight_world.mono.domain.menu.model.Menu;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AddMenuResponseDto(

        String menu_id,
        String menu_name,
        BigDecimal price,
        String description,
        String status
) {

    public static AddMenuResponseDto from(Menu menu) {

        return AddMenuResponseDto.builder()
                .menu_id(menu.getId())
                .menu_name(menu.getName())
                .price(menu.getMenuPrice().getValue())
                .description(menu.getMenuDescription().getValue())
                .status(menu.getStatus().getStatus())
                .build();
    }
}
