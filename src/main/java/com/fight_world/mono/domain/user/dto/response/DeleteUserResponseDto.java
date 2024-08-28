package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import lombok.Builder;

@Builder
public record DeleteUserResponseDto(

        String deletedAt
) {

    public static DeleteUserResponseDto from(User user) {
        return DeleteUserResponseDto.builder()
                .deletedAt(String.valueOf(user.getDeletedAt()))
                .build();
    }
}
