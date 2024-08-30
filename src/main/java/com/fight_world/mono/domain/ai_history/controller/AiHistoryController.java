package com.fight_world.mono.domain.ai_history.controller;

import static com.fight_world.mono.domain.ai_history.message.SuccessMessage.CREATED_AI_PRODUCT_DESCRIPTION;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
import com.fight_world.mono.domain.ai_history.service.AiHistoryService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class AiHistoryController {

    private final AiHistoryService aiHistoryService;

    @PostMapping("/ai/product-description")
    public ResponseEntity<? extends CommonResponse> createAiProductDescription(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody AiProductDescriptionHistoryCreateRequestDto requestDto
    ) {

        return ResponseEntity.status(CREATED_AI_PRODUCT_DESCRIPTION.getStatus())
                .body(success(CREATED_AI_PRODUCT_DESCRIPTION.getMessage(), aiHistoryService.createAiProductDescription(userDetails, requestDto)));
    }
}
