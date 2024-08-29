package com.fight_world.mono.domain.review.dto.request;

public record ReviewModifyRequestDto(
        String content,
        Integer star
) {

}
