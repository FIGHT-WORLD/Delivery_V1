package com.fight_world.mono.domain.ai_history.dto.request;

import com.fight_world.mono.domain.ai_history.model.AiHistory;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DeleteAiResponseDto(
        LocalDateTime deletedAt

) {

    public static DeleteAiResponseDto from(AiHistory aiHistory) {
        return DeleteAiResponseDto.builder()
                .deletedAt(aiHistory.getDeletedAt())
                .build();
    }
}
