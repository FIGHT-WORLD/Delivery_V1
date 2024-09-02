package com.fight_world.mono.domain.review.service;

import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.dto.request.ReviewModifyRequestDto;
import com.fight_world.mono.domain.review.dto.response.ReviewResponseDto;
import com.fight_world.mono.domain.review.model.Review;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    ReviewResponseDto createReview(UserDetailsImpl userDetails, ReviewCreateRequestDto reviewCreateRequestDto);

    Page<ReviewResponseDto> getReviews(UserDetailsImpl userDetails, Pageable pageable);

    ReviewResponseDto getReview(UserDetailsImpl userDetails, String reviewId);

    void deleteReview(UserDetailsImpl userDetails, String reviewId);

    Page<ReviewResponseDto> getStoreReviews(String storeId, Pageable pageable);

    ReviewResponseDto modifyReview(UserDetailsImpl userDetails, String reviewId, ReviewModifyRequestDto reviewModifyRequestDto);

    Review findByIdAndDeletedAtIsNull(String id);
}
