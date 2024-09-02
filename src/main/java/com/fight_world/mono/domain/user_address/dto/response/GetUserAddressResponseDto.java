package com.fight_world.mono.domain.user_address.dto.response;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetUserAddressResponseDto(

        Long userId,
        String userAddressId,
        String address,
        String detailAddress,
        String request,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime updatedAt,
        Long updatedBy,
        LocalDateTime deletedAt,
        Long deletedBy
) {
    public static GetUserAddressResponseDto from(UserAddress userAddress) {

        return GetUserAddressResponseDto.builder()
                .userId(userAddress.getUser().getId())
                .userAddressId(userAddress.getId())
                .address(userAddress.getAddress())
                .detailAddress(userAddress.getDetailAddress())
                .request(userAddress.getRequest())
                .createdAt(userAddress.getCreatedAt())
                .createdBy(userAddress.getCreatedBy())
                .updatedAt(userAddress.getUpdatedAt())
                .updatedBy(userAddress.getUpdatedBy())
                .deletedAt(userAddress.getDeletedAt())
                .deletedBy(userAddress.getDeletedBy())
                .build();
    }

}
