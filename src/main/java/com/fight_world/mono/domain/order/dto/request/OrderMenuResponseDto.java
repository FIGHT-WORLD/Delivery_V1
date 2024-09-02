package com.fight_world.mono.domain.order.dto.request;

import com.fight_world.mono.domain.order.model.OrderMenu;
import com.fight_world.mono.domain.order_menu_history.entity.OrderMenuHistory;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
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

    public static OrderMenuResponseDto from(OrderMenuHistory orderMenuHistory) {

        return OrderMenuResponseDto.builder()
                .menu_id(orderMenuHistory.getMenuId())
                .menu_name(orderMenuHistory.getName())
                .menu_price(orderMenuHistory.getPrice())
                .cnt(orderMenuHistory.getCnt())
                .build();
    }
}
