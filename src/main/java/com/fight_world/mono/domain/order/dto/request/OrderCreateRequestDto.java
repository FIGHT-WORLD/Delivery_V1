package com.fight_world.mono.domain.order.dto.request;

import java.util.List;

public record OrderCreateRequestDto(
    String store_id,
    List<OrderMenuCreateRequestDto> menu_ids,
    String delivery_type,
    String address,
    String detail_address,
    String request
) {

}