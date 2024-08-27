package com.fight_world.mono.domain.order.dto.response;

import java.math.BigDecimal;

public record OrderMenuResponseDto (
        String menu_id,
        String menu_name,
        BigDecimal menu_price,
        Integer cnt
){

}
