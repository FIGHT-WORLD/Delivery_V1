package com.fight_world.mono.domain.ai_history.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AiHistoryResponseDto(
        String message
) {

    public static AiHistoryResponseDto of(String message) {

        return AiHistoryResponseDto.builder().message(message).build();
    }
}
