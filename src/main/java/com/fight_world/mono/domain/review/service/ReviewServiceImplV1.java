package com.fight_world.mono.domain.review.service;

import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.order.service.OrderService;
import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.dto.response.ReviewResponseDto;
import com.fight_world.mono.domain.review.exception.ReviewException;
import com.fight_world.mono.domain.review.message.ExceptionMessage;
import com.fight_world.mono.domain.review.model.Review;
import com.fight_world.mono.domain.review.repository.ReviewRepository;
import com.fight_world.mono.domain.user.model.User;
import com.fight_world.mono.domain.user.service.UserService;
import com.fight_world.mono.global.security.UserDetailsImpl;
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

        User user = userService.findById(userDetails.getUser().getId());
        Order order = orderService.findById(reviewCreateRequestDto.order_id());

        if (reviewRepository.existsByOrderId(order.getId())) {
            throw new ReviewException(ExceptionMessage.ALREADY_HAS_REVIEW);
        }

        if (!order.getUser().getId().equals(user.getId())) {
            throw new ReviewException(ExceptionMessage.GUARD);
        }

        Review savedReview = reviewRepository.save(Review.of(reviewCreateRequestDto, user, order));

        return ReviewResponseDto.from(savedReview);
    }
}
