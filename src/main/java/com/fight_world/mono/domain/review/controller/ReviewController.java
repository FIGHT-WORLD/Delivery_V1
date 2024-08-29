package com.fight_world.mono.domain.review.controller;

import static com.fight_world.mono.domain.review.message.SuccessMessage.*;
import static com.fight_world.mono.global.response.SuccessResponse.*;

import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.service.ReviewService;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<? extends CommonResponse> createReview(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ReviewCreateRequestDto requestDto
    ) {

        return ResponseEntity.status(CREATED_REVIEW.getStatus())
                .body(success(CREATED_REVIEW.getMessage(), reviewService.createReview(userDetails, requestDto)));
    }

    @GetMapping("/reviews")
    public ResponseEntity<? extends CommonResponse> getReviews(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_REVIEWS.getStatus())
                .body(success(GET_REVIEWS.getMessage(), reviewService.getReview(userDetails)));
    }
}
