package com.fight_world.mono.domain.review.controller;

import static com.fight_world.mono.domain.review.message.SuccessMessage.*;
import static com.fight_world.mono.global.response.SuccessResponse.*;

import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.dto.request.ReviewModifyRequestDto;
import com.fight_world.mono.domain.review.service.ReviewService;
import com.fight_world.mono.global.aop.page.PageSizeLimit;
import com.fight_world.mono.global.response.CommonResponse;
import com.fight_world.mono.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @PostMapping("/reviews")
    public ResponseEntity<? extends CommonResponse> createReview(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ReviewCreateRequestDto requestDto
    ) {

        return ResponseEntity.status(CREATED_REVIEW.getStatus())
                .body(success(CREATED_REVIEW.getMessage(), reviewService.createReview(userDetails, requestDto)));
    }

    @PageSizeLimit
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @GetMapping("/reviews")
    public ResponseEntity<? extends CommonResponse> getReviews(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {

        return ResponseEntity.status(GET_REVIEWS.getStatus())
                .body(success(GET_REVIEWS.getMessage(), reviewService.getReviews(userDetails, pageable)));
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<? extends CommonResponse> getReview(
            @PathVariable String reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_REVIEW.getStatus())
                .body(success(GET_REVIEW.getMessage(), reviewService.getReview(userDetails, reviewId)));
    }

    @PageSizeLimit
    @GetMapping("/store/{storeId}/reviews")
    public ResponseEntity<? extends CommonResponse> getStoreReviews(
            @PathVariable String storeId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {

        return ResponseEntity.status(GET_REVIEW.getStatus())
                .body(success(GET_REVIEW.getMessage(), reviewService.getStoreReviews(storeId, pageable)));
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<? extends CommonResponse> deleteReview(
            @PathVariable String reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        reviewService.deleteReview(userDetails, reviewId);

        return ResponseEntity.status(DELETE_REVIEW.getStatus())
                .body(success(DELETE_REVIEW.getMessage()));
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_MASTER')")
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<? extends CommonResponse> modifyReview(
            @RequestBody ReviewModifyRequestDto requestDto,
            @PathVariable String reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(GET_REVIEW.getStatus())
                .body(success(GET_REVIEW.getMessage(), reviewService.modifyReview(userDetails, reviewId, requestDto)));
    }
}
