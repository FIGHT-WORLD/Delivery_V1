package com.fight_world.mono.domain.deliveryArea.dto.request;

import java.util.List;

public record RegisterDeliveryAreaRequestDto(

        String storeId,
        List<String> areaId
) {

}
