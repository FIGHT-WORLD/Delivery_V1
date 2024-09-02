package com.fight_world.mono.domain.ai_history.service;

import com.fight_world.mono.domain.ai_history.dto.AiHistoryResponseDto;
import com.fight_world.mono.domain.ai_history.dto.GetAiResponseDto;
import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
import com.fight_world.mono.domain.ai_history.dto.request.DeleteAiResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AiHistoryService {

    AiHistoryResponseDto createAiProductDescription(UserDetailsImpl userDetails,
            AiProductDescriptionHistoryCreateRequestDto requestDto);

    Page<GetAiResponseDto> getAllAiRequest(UserDetailsImpl userDetails, Pageable pageable);

    DeleteAiResponseDto deleteAiRequest(String airequestId, UserDetailsImpl userDetails);

    Page<GetAiResponseDto> getAiRequest(UserDetailsImpl userDetails, String storeId, Pageable pageable);
}
