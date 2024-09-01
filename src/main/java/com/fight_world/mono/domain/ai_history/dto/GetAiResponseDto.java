package com.fight_world.mono.domain.ai_history.dto;

import com.fight_world.mono.domain.ai_history.model.AiHistory;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetAiResponseDto(
        String storeId,
        LocalDateTime createdAt,
        String asking,
        String answer,
        String menuId,
        String promptId
) {

    public static GetAiResponseDto from(AiHistory aiHistory) {
        String storeId = aiHistory.getStore() != null ? aiHistory.getStore().getId() : null;
        String menuId = aiHistory.getMenu() != null ? aiHistory.getMenu().getId() : null;
        String promptId = aiHistory.getPrompt() != null ? aiHistory.getPrompt().getId() : null;

        return GetAiResponseDto.builder()
                .storeId(storeId)
                .menuId(menuId)
                .asking(aiHistory.getAsking())
                .answer(aiHistory.getAnswer())
                .createdAt(aiHistory.getCreatedAt())
                .promptId(promptId)
                .build();
    }
}
