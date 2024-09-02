package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SearchUserResponseDto(
        Long userId,
        String username,
        String email,
        String nickname,
        String role,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long modifiedBy,
        LocalDateTime deletedAt,
        Long deletedBy
) {
    public static SearchUserResponseDto from(User user) {

        return SearchUserResponseDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail().getValue())
                .nickname(user.getNickname())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .modifiedBy(user.getModifiedBy())
                .deletedAt(user.getDeletedAt())
                .deletedBy(user.getDeletedBy())
                .build();
    }
}