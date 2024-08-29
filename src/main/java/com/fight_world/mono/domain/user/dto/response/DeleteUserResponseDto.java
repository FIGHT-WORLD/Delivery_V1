package com.fight_world.mono.domain.user.dto.response;

import com.fight_world.mono.domain.user.model.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DeleteUserResponseDto(

        LocalDateTime deletedAt
) {

    public static DeleteUserResponseDto from(User user) {
        return DeleteUserResponseDto.builder()
                .deletedAt(user.getDeletedAt())
                .build();
    }
}
