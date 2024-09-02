package com.fight_world.mono.domain.ai_prompt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_ai_prompt")
@Builder(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiPrompt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AiPromptEnum name;

    @Column(name = "prompt", nullable = false, columnDefinition = "TEXT")
    private String prompt;

    public static AiPrompt from(AiPromptEnum aiPromptEnum) {

        return AiPrompt.builder().name(aiPromptEnum).prompt(aiPromptEnum.getPrompt()).build();
    }

    public void updatePrompt(String prompt) {

        this.prompt = prompt;
    }
}
