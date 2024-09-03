package com.fight_world.mono.domain.order.dto.request;

import java.util.List;

public record OrderUpdateRequestDto(
        String store_id,
        List<OrderMenuUpdateRequestDto> menu_ids,
        String delivery_type,
        String address,
        String detail_address,
        String request
) {

}
