package com.fight_world.mono.domain.user.model.value_object;

import com.fight_world.mono.domain.user.exception.UserException;
import com.fight_world.mono.domain.user.message.ExceptionMessage;
import jakarta.persistence.Column;

public record UserEmail(
        @Column(name = "email", nullable = false, unique = true)
        String value
) {

    public UserEmail {
        if (value == null || value.isBlank() || value.length() < 3) {
            throw new UserException(ExceptionMessage.USER_EMAIL_VALID);
        }
    }
}
