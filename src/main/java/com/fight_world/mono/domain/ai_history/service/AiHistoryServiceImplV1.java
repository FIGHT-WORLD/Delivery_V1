package com.fight_world.mono.domain.ai_history.service;

import static com.fight_world.mono.domain.ai_history.message.ExceptionMessage.DELETE;
import static com.fight_world.mono.domain.ai_history.message.ExceptionMessage.GUARD;
import static com.fight_world.mono.domain.ai_history.message.ExceptionMessage.NOTFUND;
import static com.fight_world.mono.domain.ai_history.message.ExceptionMessage.OWNER;
import static com.fight_world.mono.domain.store.message.ExceptionMessage.STORE_NOT_FOUND;

import com.fight_world.mono.domain.ai_history.dto.AiHistoryResponseDto;
import com.fight_world.mono.domain.ai_history.dto.GetAiResponseDto;
import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
import com.fight_world.mono.domain.ai_history.dto.request.DeleteAiResponseDto;
import com.fight_world.mono.domain.ai_history.exception.AiHistoryException;
import com.fight_world.mono.domain.ai_history.model.AiHistory;
import com.fight_world.mono.domain.ai_history.model.gemini.GeminiModel;
import com.fight_world.mono.domain.ai_history.model.gemini.GeminiRequest;
import com.fight_world.mono.domain.ai_history.model.gemini.GeminiResponse;
import com.fight_world.mono.domain.ai_history.model.gemini.GeminiResponse.TextPart;
import com.fight_world.mono.domain.ai_history.repository.AiHistoryRepository;
import com.fight_world.mono.domain.ai_prompt.model.AiPrompt;
import com.fight_world.mono.domain.ai_prompt.service.AiPromptService;
import com.fight_world.mono.domain.menu.model.Menu;
import com.fight_world.mono.domain.menu.service.MenuService;
import com.fight_world.mono.domain.report.exception.ReportException;
import com.fight_world.mono.domain.report.message.ExceptionMessage;
import com.fight_world.mono.domain.store.exception.StoreException;
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.repository.StoreRepository;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AiHistoryServiceImplV1 implements AiHistoryService {

    private final AiHistoryRepository aiHistoryRepository;
    private final GeminiModel geminiModel;
    private final StoreService storeService;
    private final MenuService menuService;
    private final AiPromptService aiPromptService;
    private final StoreRepository storeRepository;

    public static final String GEMINI_PRO = "gemini-pro";

    @Override
    @Transactional
    public AiHistoryResponseDto createAiProductDescription(
            UserDetailsImpl userDetails,
            AiProductDescriptionHistoryCreateRequestDto requestDto) {

        Store store = storeService.findById(requestDto.store_id());

        if (!store.getUser().getId().equals(userDetails.getUserId())) {
            throw new AiHistoryException(GUARD);
        }

        Menu menu = null;

        if (requestDto.menu_id() != null) {
            menu = menuService.findById(requestDto.menu_id());
        }

        AiPrompt aiPrompt = aiPromptService.findById(requestDto.prompt_id());

        StringBuilder sb = new StringBuilder();
        sb.append("가게명: ").append(store.getName())
                .append(" 메뉴명:").append(menu != null ? menu.getName() : null)
                .append(aiPrompt.getPrompt())
                .append(requestDto.asking());

        GeminiResponse completion = geminiModel.getCompletion(GEMINI_PRO,
                new GeminiRequest(sb.toString()));

        String answer = completion.getCandidates()
                .stream()
                .findFirst()
                .flatMap(candidate -> candidate.getContent()
                        .getParts()
                        .stream()
                        .findFirst()
                        .map(TextPart::getText))
                .orElse(null);

        answer = answer.replaceAll("\\n", " ");

        AiHistory savedAiHistory = aiHistoryRepository.save(
                AiHistory.of(store, requestDto, menu, answer, aiPrompt));

        return AiHistoryResponseDto.of(savedAiHistory.getAnswer());
    }

    // ai 요청 기록 조회(관리자)
    @Transactional
    public Page<GetAiResponseDto> getAllAiRequest(UserDetailsImpl userDetails, Pageable pageable) {

        if (userDetails.getUser().getRole() != UserRole.MASTER) {
            throw new ReportException(ExceptionMessage.REPORT_ADMIN);
        }

        Page<AiHistory> aiHistories = aiHistoryRepository.findAll(pageable);

        return aiHistories.map(aiHistory -> GetAiResponseDto.from(aiHistory));
    }

    // ai 요청 기록 삭제(관리자)
    @Transactional
    public DeleteAiResponseDto deleteAiRequest(String airequestId, UserDetailsImpl userDetails) {

        if (userDetails.getUser().getRole() != UserRole.MASTER) {
            throw new AiHistoryException(DELETE);
        }

        AiHistory aiHistory = findById(airequestId);
        aiHistory.deletedAt(userDetails.getUserId());

        return DeleteAiResponseDto.from(aiHistory);
    }

    // ai 요청 기록 조회(Owner)
    @Transactional
    public Page<GetAiResponseDto> getAiRequest(UserDetailsImpl userDetails, String storeId,
            Pageable pageable) {

        if (userDetails.getUser().getRole() != UserRole.OWNER) {
            throw new AiHistoryException(OWNER);
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
        if (!store.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new AiHistoryException(OWNER);
        }

        Page<AiHistory> aiHistories = aiHistoryRepository.findAllByStoreId(storeId, pageable);

        return aiHistories.map(GetAiResponseDto::from);
    }

    @Transactional
    public AiHistory findById(String airequestId) {
        return aiHistoryRepository.findById(airequestId)
                .orElseThrow(() -> new AiHistoryException(NOTFUND));

    }
}
