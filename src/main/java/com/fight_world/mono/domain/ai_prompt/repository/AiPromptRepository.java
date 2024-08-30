package com.fight_world.mono.domain.ai_prompt.repository;

import com.fight_world.mono.domain.ai_prompt.model.AiPrompt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiPromptRepository extends JpaRepository<AiPrompt,String> {

}
