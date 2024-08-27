package com.fight_world.mono.domain.store.model.value_object;

import com.fight_world.mono.domain.store.exception.StoreException;
import com.fight_world.mono.domain.store.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class StorePhoneNumber {

    @Column(name = "phone_number", nullable = false)
    String value;

    public StorePhoneNumber(String value) {
        if (value == null || value.isBlank() || value.length() > 50) {
            throw new StoreException(ExceptionMessage.STORE_PHONE_NUMBER_VALID);
        }
        this.value = value;
    }
}
