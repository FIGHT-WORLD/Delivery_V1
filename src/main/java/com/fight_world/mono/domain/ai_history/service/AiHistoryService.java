package com.fight_world.mono.domain.ai_history.service;

import com.fight_world.mono.domain.ai_history.dto.AiHistoryResponseDto;
import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
import com.fight_world.mono.global.security.UserDetailsImpl;

public interface AiHistoryService {

    AiHistoryResponseDto createAiProductDescription(UserDetailsImpl userDetails, AiProductDescriptionHistoryCreateRequestDto requestDto);
}
