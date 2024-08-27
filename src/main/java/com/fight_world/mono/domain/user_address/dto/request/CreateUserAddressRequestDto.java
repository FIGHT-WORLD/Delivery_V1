package com.fight_world.mono.domain.user_address.dto.request;

import com.fight_world.mono.domain.user.model.User;

public record CreateUserAddressRequestDto(
        String address,
        String detailAddress,
        String request,
        User user
) { }
