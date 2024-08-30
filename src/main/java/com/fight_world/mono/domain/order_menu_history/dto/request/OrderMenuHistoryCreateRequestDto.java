package com.fight_world.mono.domain.order_menu_history.dto.request;

import com.fight_world.mono.domain.order_menu.model.OrderMenu;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OrderMenuHistoryCreateRequestDto(
        String name,
        BigDecimal price,
        String description,
        Integer cnt,
        String menuId
) {

    public static OrderMenuHistoryCreateRequestDto from(OrderMenu orderMenu) {

        return OrderMenuHistoryCreateRequestDto.builder()
                .menuId(orderMenu.getMenu().getId())
                .name(orderMenu.getMenu().getName())
                .price(orderMenu.getMenu().getMenuPrice().getValue())
                .description(orderMenu.getMenu().getMenuDescription().getValue())
                .cnt(orderMenu.getCnt().getValue())
                .build();
    }
}
