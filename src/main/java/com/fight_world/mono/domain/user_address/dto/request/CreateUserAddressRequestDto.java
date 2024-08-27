package com.fight_world.mono.domain.user_address.dto.request;

import com.fight_world.mono.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public record CreateUserAddressRequestDto(
        @NotBlank
        String address,
        @NotBlank
        String detailAddress,
        String request
) { }
