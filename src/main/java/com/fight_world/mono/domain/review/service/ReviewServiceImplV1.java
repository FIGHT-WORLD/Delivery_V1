package com.fight_world.mono.domain.review.service;

import static com.fight_world.mono.domain.review.message.ExceptionMessage.ALREADY_HAS_REVIEW;
import static com.fight_world.mono.domain.review.message.ExceptionMessage.GUARD;
import static com.fight_world.mono.domain.review.message.ExceptionMessage.NOT_FOUND_REVIEW;

import com.fight_world.mono.domain.order.exception.OrderException;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.dto.request.ReviewModifyRequestDto;
import com.fight_world.mono.domain.review.dto.response.ReviewResponseDto;
import com.fight_world.mono.domain.review.exception.ReviewException;
import com.fight_world.mono.domain.review.message.ExceptionMessage;
import com.fight_world.mono.domain.review.model.Review;
import com.fight_world.mono.domain.review.repository.ReviewRepository;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.model.UserRole;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplV1 implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderService orderService;
    private final UserService userService;

    @Override
    @Transactional
    public ReviewResponseDto createReview(
            UserDetailsImpl userDetails,
            ReviewCreateRequestDto reviewCreateRequestDto
    ) {

        User user = userService.findById(userDetails.getUserId());
        Order order = orderService.findById(reviewCreateRequestDto.order_id());

        if (reviewRepository.existsByOrderId(order.getId())) {
            throw new ReviewException(ALREADY_HAS_REVIEW);
        }

        if (!order.getUser().getId().equals(user.getId())) {
            throw new ReviewException(GUARD);
        }

        Review savedReview = reviewRepository.save(Review.of(reviewCreateRequestDto, user, order));

        return ReviewResponseDto.from(savedReview);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getReviews(
            UserDetailsImpl userDetails
    ) {

        List<Review> reviews = reviewRepository.findAllByUserIdAndDeletedAtIsNull(userDetails.getUserId());

        return reviews.stream().map(ReviewResponseDto::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponseDto getReview(UserDetailsImpl userDetails, String reviewId) {

        Review review = findByIdAndDeletedAtIsNull(reviewId);

        if (review.getIsReport()) {

            if (!review.getUser().getId().equals(userDetails.getUserId())) {
                throw new ReviewException(GUARD);
            }
        }

        return ReviewResponseDto.from(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getStoreReview(String storeId) {

        List<Review> storeReviews = reviewRepository.findAllByStoreIdAndDeletedAtIsNullAndIsReportIsFalse(
                storeId);

        return storeReviews.stream().map(ReviewResponseDto::from).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReview(UserDetailsImpl userDetails, String reviewId) {

        Review review = findByIdAndDeletedAtIsNull(reviewId);

        checkAuthority(userDetails, review);

        review.delete(userDetails.getUserId());
    }

    @Override
    @Transactional
    public ReviewResponseDto modifyReview(UserDetailsImpl userDetails, String reviewId, ReviewModifyRequestDto requestDto) {

        Review review = findByIdAndDeletedAtIsNull(reviewId);

        checkAuthority(userDetails, review);

        review.modify(requestDto);

        return ReviewResponseDto.from(review);
    }

    public void checkAuthority(UserDetailsImpl userDetails, Review review) {

        if (!checkIsAdmin(userDetails)) {
            checkIsReviewWriter(userDetails, review);
        }
    }

    public boolean checkIsAdmin(UserDetailsImpl userDetails) {

        User user = userService.findById(userDetails.getUserId());

        return user.getRole().equals(UserRole.MANAGER) || user.getRole().equals(UserRole.MASTER);
    }

    public void checkIsReviewWriter(UserDetailsImpl userDetails, Review review) {

        User user = userService.findById(userDetails.getUserId());

        if (!review.getUser().equals(user)) {
            throw new ReviewException(GUARD);
        }
    }

    @Override
    public Review findByIdAndDeletedAtIsNull(String id) {

        return reviewRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ReviewException(NOT_FOUND_REVIEW)
        );
    }


}
