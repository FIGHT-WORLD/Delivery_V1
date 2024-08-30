package com.fight_world.mono.domain.ai_prompt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AiPromptEnum {

    EMOJI("에 대한 메뉴 설명을 적어줘\n"
            + "1. 이모티콘을 많이 넣어줘\n"
            + "2. 50글자 이내로 작성해줘\n"
            + "3. 한국어로 적어줘"),

    HUMBLE("에 대한 메뉴 설명을 적어줘\n"
            + "1. 최대한 정중하게 설명해\n"
            + "2. 50글자 이내로 작성해\n"
            + "3. 한국어로 적어줘"),

    ENGLISH("에 대한 메뉴 설명을 적어줘\n"
            + "1. 맛있어 보이게 설명해\n"
            + "2. 50글자 이내로 작성해\n"
            + "3. 영어로 적어줘");


    private final String prompt;
}
