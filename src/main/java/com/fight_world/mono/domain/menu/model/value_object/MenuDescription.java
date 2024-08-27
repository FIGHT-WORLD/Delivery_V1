package com.fight_world.mono.domain.menu.model.value_object;

import com.fight_world.mono.domain.menu.exception.MenuException;
import com.fight_world.mono.domain.menu.message.ExceptionMessage;
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
public class MenuDescription {

    @Column(name = "description", nullable = false)
    String value;

    public MenuDescription(String value) {
        if (value == null || value.isBlank() || value.length() > 50) {
            throw new MenuException(ExceptionMessage.MENU_DESCRIPTION_VALID);
        }
        this.value = value;
    }
}
