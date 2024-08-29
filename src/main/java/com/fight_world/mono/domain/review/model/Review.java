package com.fight_world.mono.domain.review.model;

import com.fight_world.mono.domain.model.TimeBase;
import com.fight_world.mono.domain.order.model.Order;
import com.fight_world.mono.domain.review.dto.request.ReviewCreateRequestDto;
import com.fight_world.mono.domain.review.model.value_object.ReviewContent;
import com.fight_world.mono.domain.review.model.value_object.ReviewStar;
import com.fight_world.mono.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_review")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Embedded
    private ReviewStar star;

    @Embedded
    private ReviewContent content;

    @Column(nullable = false)
    private Boolean isReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Builder(access = AccessLevel.PRIVATE)
    public Review(ReviewStar star, ReviewContent content, Boolean isReport, User user,
            Order order) {
        this.star = star;
        this.content = content;
        this.isReport = isReport;
        this.user = user;
        this.order = order;
    }

    public static Review of(ReviewCreateRequestDto reviewCreateRequestDto, User user, Order order) {

        return Review.builder()
                .star(new ReviewStar(reviewCreateRequestDto.star()))
                .content(new ReviewContent(reviewCreateRequestDto.content()))
                .isReport(reviewCreateRequestDto.isReport())
                .user(user)
                .order(order)
                .build();
    }

    public void delete(Long userId) {

        super.setDeleted(user.getId());
    }
}
