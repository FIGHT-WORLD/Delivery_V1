package com.fight_world.mono.domain.order.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDetailResponseDto(
        String order_id,
        String store_id,
        String store_name,
        List<OrderMenuResponseDto> menu_ids,
        String delivery_type,
        LocalDateTime created_at,
        String order_status,
        String address,
        String detail_address,
        String request,
        BigDecimal total_price,
        String payment_type
) {

}
