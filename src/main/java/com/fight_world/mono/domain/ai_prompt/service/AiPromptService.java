package com.fight_world.mono.domain.ai_prompt.service;

import com.fight_world.mono.domain.ai_prompt.model.AiPrompt;

public interface AiPromptService {

    AiPrompt findById(String id);
}
