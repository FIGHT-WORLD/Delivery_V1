package com.fight_world.mono.domain.review.model.value_object;

import com.fight_world.mono.domain.review.exception.ReviewException;
import com.fight_world.mono.domain.review.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
public class ReviewStar {

    @Column(name = "star", nullable = false)
    private Integer value;

    public ReviewStar(Integer value) {

        if (value == null || value < 1 || value > 5) {

            throw new ReviewException(ExceptionMessage.REVIEW_STAR_VALID);
        }

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReviewStar that = (ReviewStar) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}
