package com.fight_world.mono.domain.user_address.dto.response;

import com.fight_world.mono.domain.user_address.model.UserAddress;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DeleteUserAddressResponseDto(
        LocalDateTime deletedAt
) {

    public static DeleteUserAddressResponseDto from(UserAddress userAddress) {

        return DeleteUserAddressResponseDto.builder()
                .deletedAt(userAddress.getDeletedAt())
                .build();
    }
}
