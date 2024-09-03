package com.fight_world.mono.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

public record ModifyStoreRequestDto(

        @NotBlank(message = "가게 이름은 필수입니다.")
        @Size(max = 100, message = "가게 이름은 최대 100자까지 가능합니다.")
        String name,

        @NotBlank(message = "가게 주소는 필수 입력값입니다.")
        @Size(max = 255, message = "가게 주소는 최대 255자까지 가능합니다.")
        String address,

        @NotNull(message = "영업 시작 시간은 필수 입력값입니다.")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime openAt,

        @NotNull(message = "영업 종료 시간은 필수 입력값입니다.")
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime closeAt,

        @NotBlank(message = "전화번호는 필수 입력값입니다.")
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 유효하지 않습니다.")
        String phoneNumber,

        @NotBlank(message = "음식점 카테고리는 필수 입력값입니다.")
        String storeCategoryId
) {

}
