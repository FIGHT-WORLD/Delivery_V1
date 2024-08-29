package com.fight_world.mono.domain.ai_history.dto.request;

public record AiProductDescriptionHistoryCreateRequestDto (
        String store_id,
        String menu_id,
        String prompt_id,
        String asking
) {
}
