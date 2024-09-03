package com.fight_world.mono.domain.store.dto.response;

import com.fight_world.mono.domain.store.model.Store;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record StoreResponseDto (

        String store_id,

        String name,

        String address,

        LocalTime open_at,

        LocalTime close_at,

        String phone_number,

        String store_category,

        String status
) {

    public static StoreResponseDto from(Store store) {

        return StoreResponseDto.builder()
                .store_id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .open_at(store.getOpenAt())
                .close_at(store.getCloseAt())
                .phone_number(store.getPhoneNumber().getValue())
                .store_category(store.getStoreCategory().getCategoryName())
                .status(store.getStatus().getStatus())
                .build();
    }

    public static StoreResponseDto from(DeliveryAvailableStoresResponseDto store) {

        return StoreResponseDto.builder()
                .store_id(store.storeId())
                .name(store.name())
                .address(store.address())
                .open_at(store.openAt())
                .close_at(store.closeAt())
                .phone_number(store.phoneNumber().getValue())
                .store_category(store.storeCategory())
                .status(store.status().getStatus())
                .build();
    }
}
