package com.fight_world.mono.domain.menu.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangeMenuStatusRequestDto(

        @NotBlank(message = "메뉴 상태는 필수 입력값입니다.")
        String status
) {

}
