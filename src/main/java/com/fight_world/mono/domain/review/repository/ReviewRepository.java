package com.fight_world.mono.domain.review.repository;

import com.fight_world.mono.domain.review.model.Review;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, String> {

    boolean existsByOrderId(String orderId);

    Page<Review> findAllByUserIdAndDeletedAtIsNull(Long userId, Pageable pageable);

    Optional<Review> findByIdAndDeletedAtIsNull(String reviewId);

    @Query("SELECT r "
            + "FROM Review r "
            + "JOIN FETCH Order o ON r.order.id = o.id "
            + "JOIN FETCH p_store s ON o.store.id = s.id "
            + "WHERE s.id = :storeId AND r.deletedAt = null AND r.isReport = false")
    Page<Review> findAllByStoreIdAndDeletedAtIsNullAndIsReportIsFalse(String storeId,
            Pageable pageable);
}
