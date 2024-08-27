package com.fight_world.mono.domain.order.dto.response;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order_menu.dto.reponse.OrderMenuResponseDto;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OrderResponseDto(

        String order_id,

        String store_id,

        String store_name,

        Set<OrderMenuResponseDto> menu_ids,

        String delivery_type,

        LocalDateTime created_at,

        String order_status
) {

    public static OrderResponseDto of(Order order) {

        return OrderResponseDto.builder()
                               .order_id(order.getId())
                               .store_id(order.getStore().getId())
                               .store_name(order.getStore().getName())
                               .menu_ids(order.getOrderMenus()
                                       .stream()
                                       .map(o -> OrderMenuResponseDto.of(o)).collect(Collectors.toSet()))
                               .delivery_type(order.getDeliveryType().getType())
                               .created_at(order.getCreatedAt())
                               .order_status(order.getStatus().getStatus())
                               .build();
    }
}
