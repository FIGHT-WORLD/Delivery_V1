package com.fight_world.mono.domain.review.service;

import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.dto.response.ReviewResponseDto;
import com.fight_world.mono.global.security.UserDetailsImpl;

public interface ReviewService {

    ReviewResponseDto createReview(UserDetailsImpl userDetails, ReviewCreateRequestDto reviewCreateRequestDto);
}
