package com.fight_world.mono.domain.user_address.dto.response;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record UpdateUserAddressResponseDto(
        Long userId,
        String userAddressId,
        String address,
        String detailAddress,
        String request,
        LocalDateTime updatedAt,
        Long updatedBy
) {
    public static UpdateUserAddressResponseDto from(UserAddress userAddress) {

        return UpdateUserAddressResponseDto.builder()
                .userId(userAddress.getUser().getId())
                .userAddressId(userAddress.getId())
                .address(userAddress.getAddress())
                .detailAddress(userAddress.getDetailAddress())
                .request(userAddress.getRequest())
                .updatedAt(userAddress.getUpdatedAt())
                .updatedBy(userAddress.getUpdatedBy())
                .build();
    }
}
