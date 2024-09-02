package com.fight_world.mono.domain.deliveryArea.dto.response;

import com.fight_world.mono.domain.deliveryArea.model.DeliveryArea;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record DeliveryAreaResponseDto(

        String delivery_area_id,
        String area_code,
        String area_name
) {

    public static DeliveryAreaResponseDto from(DeliveryArea deliveryArea) {

        return DeliveryAreaResponseDto.builder()
                .delivery_area_id(deliveryArea.getId())
                .area_code(deliveryArea.getAddressDongeupmyun().getCode())
                .area_name(deliveryArea.getAddressDongeupmyun().getName())
                .build();
    }
}
