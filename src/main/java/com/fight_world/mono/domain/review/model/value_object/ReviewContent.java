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
public class ReviewContent {

    @Column(name = "content", nullable = false, length = 500)
    private String value;

    public ReviewContent(String value) {

        if (value == null || value.isBlank() || value.length() < 3) {

            throw new ReviewException(ExceptionMessage.REVIEW_CONTENT_VALID_MIN);
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

        ReviewContent that = (ReviewContent) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}
