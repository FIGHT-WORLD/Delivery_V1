package com.fight_world.mono.domain.review.service;

import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.dto.request.ReviewModifyRequestDto;
import com.fight_world.mono.domain.review.dto.response.ReviewResponseDto;
import com.fight_world.mono.domain.review.model.Review;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;

public interface ReviewService {

    ReviewResponseDto createReview(UserDetailsImpl userDetails, ReviewCreateRequestDto reviewCreateRequestDto);

    List<ReviewResponseDto> getReviews(UserDetailsImpl userDetails);

    ReviewResponseDto getReview(UserDetailsImpl userDetails, String reviewId);

    void deleteReview(UserDetailsImpl userDetails, String reviewId);

    List<ReviewResponseDto> getStoreReview(String storeId);

    ReviewResponseDto modifyReview(UserDetailsImpl userDetails, String reviewId, ReviewModifyRequestDto reviewModifyRequestDto);

    Review findByIdAndDeletedAtIsNull(String id);
}
