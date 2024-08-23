package com.fight_world.mono.domain.user.model.value_object.converter;

import com.fight_world.mono.domain.user.model.value_object.UserEmail;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserEmailConverter implements AttributeConverter<UserEmail, String> {

    @Override
    public String convertToDatabaseColumn(UserEmail userEmail) {

        if (userEmail == null) {
            return null;
        }

        return userEmail.value();
    }

    @Override
    public UserEmail convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return new UserEmail(dbData);
    }
}
