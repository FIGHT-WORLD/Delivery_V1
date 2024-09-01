package com.fight_world.mono.domain.user_address.dto.response;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user_address.model.UserAddress;
import lombok.Builder;

@Builder
public record GetUserAddressListResponseDto(

        Long userId,
        String userAddressId,
        String address,
        String detailAddress,
        String request
) {
    public static GetUserAddressListResponseDto of(UserAddress userAddress) {

        return GetUserAddressListResponseDto.builder()
                .userId(userAddress.getUser().getId())
                .userAddressId(userAddress.getId())
                .address(userAddress.getAddress())
                .detailAddress(userAddress.getDetailAddress())
                .request(userAddress.getRequest())
                .build();
    }
}
