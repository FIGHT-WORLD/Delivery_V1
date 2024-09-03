package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DeleteUserResponseDto(

        Long deletedBy,
        LocalDateTime deletedAt
) {

    public static DeleteUserResponseDto from(User user) {
        return DeleteUserResponseDto.builder()
                .deletedBy(user.getId())
                .deletedAt(user.getDeletedAt())
                .build();
    }
}
