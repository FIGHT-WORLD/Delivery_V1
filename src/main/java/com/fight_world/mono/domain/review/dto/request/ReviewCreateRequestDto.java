package com.fight_world.mono.domain.review.dto.request;

public record ReviewCreateRequestDto(
        String order_id,
        Integer star,
        String content,
        Boolean isReport
) {

}
