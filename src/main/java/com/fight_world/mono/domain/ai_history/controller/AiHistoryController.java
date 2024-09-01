package com.fight_world.mono.domain.ai_history.controller;

import static com.fight_world.mono.domain.ai_history.message.SuccessMessage.CREATED_AI_PRODUCT_DESCRIPTION;
import static com.fight_world.mono.domain.ai_history.message.SuccessMessage.DELETE_AI_REQUEST;
import static com.fight_world.mono.domain.ai_history.message.SuccessMessage.GET_AI_REQUEST;
import static com.fight_world.mono.global.response.SuccessResponse.success;

import com.fight_world.mono.domain.ai_history.dto.GetAiResponseDto;
import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
import com.fight_world.mono.domain.ai_history.dto.request.DeleteAiResponseDto;
import com.fight_world.mono.domain.ai_history.service.AiHistoryService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                .body(success(CREATED_AI_PRODUCT_DESCRIPTION.getMessage(),
                        aiHistoryService.createAiProductDescription(userDetails, requestDto)));
    }


    // ai 요청 기록 목록 조회(관리자)
    @GetMapping("/ai/admin/ai-request")
    public ResponseEntity<? extends CommonResponse> getAiRequest(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<GetAiResponseDto> responseDto = aiHistoryService.getAllAiRequest(userDetails,
                pageable);

        return ResponseEntity.status(GET_AI_REQUEST.getStatus())
                .body(success(GET_AI_REQUEST.getMessage(), responseDto));
    }


    // ai 요청 기록 조회(OWNER)
    @GetMapping("/ai/ai-request")
    public ResponseEntity<? extends CommonResponse> getAiRequestOwner(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        String storeId = String.valueOf(userDetails.getUser().getId());
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<GetAiResponseDto> responseDto = aiHistoryService.getAiRequest(userDetails, storeId, pageable);

        return ResponseEntity.status(GET_AI_REQUEST.getStatus())
                .body(success(GET_AI_REQUEST.getMessage(), responseDto));
    }

    // ai 요청 기록 삭제(관리자)
    @DeleteMapping("/ai/{airequestId}")
    public ResponseEntity<? extends CommonResponse> deleteAiResuest(
            @PathVariable("airequestId") String airequestId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        DeleteAiResponseDto responseDto = aiHistoryService.deleteAiRequest(airequestId,
                userDetails);

        return ResponseEntity.status(DELETE_AI_REQUEST.getStatus())
                .body(success(DELETE_AI_REQUEST.getMessage(), responseDto));
    }
}


