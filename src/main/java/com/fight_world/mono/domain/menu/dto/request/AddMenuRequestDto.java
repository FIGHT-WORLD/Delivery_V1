package com.fight_world.mono.domain.menu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record AddMenuRequestDto(

        @NotBlank(message = "가게의 id는 입력값입니다.")
        String store_id,

        @NotBlank(message = "메뉴명은 필수 입력값입니다.")
        String name,

        BigDecimal price,

        @NotBlank(message = "메뉴 설명은 필수 입력값입니다.")
        @Size(max = 50, message = "메뉴 설명은 최대 255자까지 가능합니다.")
        String description
) {

}
