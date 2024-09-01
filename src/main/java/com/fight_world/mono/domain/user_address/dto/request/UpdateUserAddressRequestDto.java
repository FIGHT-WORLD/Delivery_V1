package com.fight_world.mono.domain.user_address.dto.request;

public record UpdateUserAddressRequestDto(
        String address,
        String detailAddress,
        String request
) {

}
