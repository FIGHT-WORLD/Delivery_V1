package com.fight_world.mono.domain.deliveryArea.dto.request;

import java.util.List;

public record RegisterDeliveryAreaRequestDto(

        String store_id,
        List<String> area_codes
) {

}
