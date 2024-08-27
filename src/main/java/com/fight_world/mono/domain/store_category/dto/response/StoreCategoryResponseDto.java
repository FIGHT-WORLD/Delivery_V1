package com.fight_world.mono.domain.store_category.dto.response;

import com.fight_world.mono.domain.store_category.model.StoreCategory;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record StoreCategoryResponseDto(

        String category_id,

        String name
) {

    public static StoreCategoryResponseDto of(StoreCategory storeCategory) {

        return StoreCategoryResponseDto.builder()
                .category_id(storeCategory.getId())
                .name(storeCategory.getCategoryName())
                .build();
    }
}
