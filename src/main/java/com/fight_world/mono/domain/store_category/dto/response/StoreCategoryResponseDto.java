package com.fight_world.mono.domain.store_category.dto.response;

import com.fight_world.mono.domain.store_category.model.StoreCategory;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record StoreCategoryResponseDto(

        String category_id,

        String category_name
) {

    public static StoreCategoryResponseDto from(StoreCategory storeCategory) {

        return StoreCategoryResponseDto.builder()
                .category_id(storeCategory.getId())
                .category_name(storeCategory.getCategoryName())
                .build();
    }
}
