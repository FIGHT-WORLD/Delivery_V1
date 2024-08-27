package com.fight_world.mono.domain.user_address.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public record CreateUserAddressRequestDto(
        @NotBlank
        String address,
        @NotBlank
        String detailAddress,
        String request
) {

}
