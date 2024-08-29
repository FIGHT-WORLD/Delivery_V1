package com.fight_world.mono.domain.store_category.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StoreCategoryAddRequestDto(

        @NotBlank(message = "카테코리 이름은 필수입니다.")
        String category_name
) {

}
