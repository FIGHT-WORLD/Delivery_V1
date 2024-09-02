package com.fight_world.mono.domain.user_address.dto.response;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import lombok.Builder;


@Builder
public record SearchUserAddressResponseDto(
        String id,
        String address,
        String detailAddress,
        String request,
        Long userId) {
    public static SearchUserAddressResponseDto from(UserAddress userAddress) {

        return SearchUserAddressResponseDto.builder()
                .id(userAddress.getId())
                .address(userAddress.getAddress())
                .detailAddress(userAddress.getDetailAddress())
                .request(userAddress.getRequest())
                .userId(userAddress.getUser().getId())
                .build();
    }
}