package com.fight_world.mono.domain.review.repository;

import com.fight_world.mono.domain.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {

    boolean existsByOrderId(String orderId);
}
