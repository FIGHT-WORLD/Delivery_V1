package com.fight_world.mono.domain.user_address.dto.response;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CreateUserAddressResponseDto(
        Long userId,
        String userAddressId,
        String address,
        String detailAddress,
        String request,
        LocalDateTime createdAt
) {

    public static CreateUserAddressResponseDto from(UserAddress userAddress) {

        return CreateUserAddressResponseDto.builder()
                .userId(userAddress.getUser().getId())
                .userAddressId(userAddress.getId())
                .address(userAddress.getAddress())
                .detailAddress(userAddress.getDetailAddress())
                .request(userAddress.getRequest())
                .createdAt(userAddress.getCreatedAt())
                .build();
    }
}
