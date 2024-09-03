package com.fight_world.mono.domain.ai_prompt.init;

import com.fight_world.mono.domain.ai_prompt.model.AiPrompt;
import com.fight_world.mono.domain.ai_prompt.model.AiPromptEnum;
import com.fight_world.mono.domain.ai_prompt.repository.AiPromptRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiPromptInitializer {

    private final AiPromptRepository aiPromptRepository;
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        addCheckConstraint();
        initializePrompt();
    }

    private void addCheckConstraint() {
        String[] enumValue = new String[AiPromptEnum.values().length];

        for (int i = 0; i < enumValue.length; i++) {
            enumValue[i] = "'" + AiPromptEnum.values()[i].name() + "'";
        }
        String checkConstraint = "CHECK (name IN(" + String.join(", ", enumValue) + "))";

        jdbcTemplate.execute("ALTER TABLE p_ai_prompt DROP CONSTRAINT IF EXISTS p_ai_prompt_name_check");
        jdbcTemplate.execute("ALTER TABLE p_ai_prompt ADD CONSTRAINT p_ai_prompt_name_check " + checkConstraint);
    }

    private void initializePrompt() {
        for (AiPromptEnum aiPromptEnum : AiPromptEnum.values()) {
            AiPrompt aiPrompt = aiPromptRepository.findByName(aiPromptEnum).orElseGet(
                    () -> {
                        AiPrompt role = AiPrompt.from(aiPromptEnum);
                        return aiPromptRepository.save(role);
                    }
            );

            if (!aiPromptEnum.getPrompt().equals(aiPrompt.getPrompt())) {
                aiPrompt.updatePrompt(aiPromptEnum.getPrompt());
                aiPromptRepository.save(aiPrompt);
            }
        }
    }

}
