package com.fight_world.mono.domain.review.model.value_object.converter;

import com.fight_world.mono.domain.review.model.value_object.ReviewContent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReviewContentConverter implements AttributeConverter<ReviewContent, String> {

    @Override
    public String convertToDatabaseColumn(ReviewContent reviewContent) {

        if (reviewContent == null) {
            return null;
        }

        return reviewContent.getValue();
    }

    @Override
    public ReviewContent convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return new ReviewContent(dbData);
    }
}
