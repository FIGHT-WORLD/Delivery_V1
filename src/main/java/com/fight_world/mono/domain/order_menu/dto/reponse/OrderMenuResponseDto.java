package com.fight_world.mono.domain.order_menu.dto.reponse;

import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record OrderMenuResponseDto (
        String menu_id,
        String menu_name,
        BigDecimal menu_price,
        Integer cnt
){

    public static OrderMenuResponseDto from(OrderMenu orderMenu) {

        return OrderMenuResponseDto.builder()
                .menu_id(orderMenu.getMenu().getId())
                .menu_name(orderMenu.getMenu().getName())
                .menu_price(orderMenu.getMenu().getMenuPrice().getValue())
                .cnt(orderMenu.getCnt().getValue())
                .build();
    }
}
