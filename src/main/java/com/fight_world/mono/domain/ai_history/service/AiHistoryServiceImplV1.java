package com.fight_world.mono.domain.ai_history.service;

import static com.fight_world.mono.domain.ai_history.message.ExceptionMessage.GUARD;

import com.fight_world.mono.domain.ai_history.dto.AiHistoryResponseDto;
import com.fight_world.mono.domain.ai_history.dto.request.AiProductDescriptionHistoryCreateRequestDto;
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
import com.fight_world.mono.domain.store.model.Store;
import com.fight_world.mono.domain.store.service.StoreService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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

    public static final String GEMINI_PRO = "gemini-pro";

    @Override
    @Transactional
    public AiHistoryResponseDto createAiProductDescription(
            UserDetailsImpl userDetails,
            AiProductDescriptionHistoryCreateRequestDto requestDto)
    {

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

        GeminiResponse completion = geminiModel.getCompletion(GEMINI_PRO, new GeminiRequest(sb.toString()));

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

        AiHistory savedAiHistory = aiHistoryRepository.save(AiHistory.of(store, requestDto, menu, answer));

        return AiHistoryResponseDto.of(savedAiHistory.getAnswer());
    }
}
