package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import lombok.Builder;

@Builder
public record UpdateUserResponseDto(
        String password,
        String email,
        String nickname
) {

    public static UpdateUserResponseDto from(User user) {
        return UpdateUserResponseDto.builder()
                .password(user.getPassword())
                .email(user.getEmail().getValue())
                .nickname(user.getNickname())
                .build();
    }
}
