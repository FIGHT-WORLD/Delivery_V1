package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetUserResponseListDto(
        Long userId,
        String username,
        String email,
        String nickname,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long modifiedBy,
        LocalDateTime deletedAt,
        Long deletedBy
) {

    public static GetUserResponseListDto from(User user) {

        return GetUserResponseListDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail().getValue())
                .nickname(user.getNickname())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .modifiedBy(user.getUpdatedBy())
                .deletedAt(user.getDeletedAt())
                .deletedBy(user.getDeletedBy())
                .build();
    }
}
