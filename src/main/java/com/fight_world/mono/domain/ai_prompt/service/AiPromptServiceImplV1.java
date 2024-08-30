package com.fight_world.mono.domain.ai_prompt.service;

import com.fight_world.mono.domain.ai_prompt.model.AiPrompt;
import com.fight_world.mono.domain.ai_prompt.repository.AiPromptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiPromptServiceImplV1 implements AiPromptService {

    private final AiPromptRepository aiPromptRepository;

    @Override
    public AiPrompt findById(String id) {

        return aiPromptRepository.findById(id).orElse(null);
    }
}
