package com.fight_world.mono.domain.order.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        String order_id,
        String store_id,
        String store_name,
        List<OrderMenuResponseDto> menu_ids,
        String delivery_type,
        LocalDateTime created_at,
        String order_status
) {

}
