package com.fight_world.mono.domain.review.dto.response;

import com.fight_world.mono.domain.review.model.Review;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewResponseDto(
        String review_id,
        Integer star,
        String content,
        Boolean isReport
) {

    public static ReviewResponseDto from(Review review) {

        return ReviewResponseDto.builder()
                                .review_id(review.getId())
                                .star(review.getStar().getValue())
                                .content(review.getContent().getValue())
                                .isReport(review.getIsReport())
                                .build();
    }
}
