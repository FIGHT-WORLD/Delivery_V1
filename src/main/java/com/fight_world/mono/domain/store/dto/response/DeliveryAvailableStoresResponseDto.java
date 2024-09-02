package com.fight_world.mono.domain.store.dto.response;

import com.fight_world.mono.domain.store.model.constant.StoreStatus;
import com.fight_world.mono.domain.store.model.value_object.StorePhoneNumber;
import java.time.LocalTime;

public record DeliveryAvailableStoresResponseDto(

        String storeId,
        String name,
        String address,
        LocalTime openAt,
        LocalTime closeAt,
        StorePhoneNumber phoneNumber,
        String storeCategory,
        StoreStatus status
) {

}
