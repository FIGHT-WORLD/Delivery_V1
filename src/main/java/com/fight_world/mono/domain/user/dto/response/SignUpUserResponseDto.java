package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SignUpUserResponseDto(
        String username,
        String email,
        String nickname,
        String role,
        LocalDateTime createdAt
) {
    public static SignUpUserResponseDto from(User user) {
        return SignUpUserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail().getValue())
                .nickname(user.getNickname())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
