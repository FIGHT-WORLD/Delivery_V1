package com.fight_world.mono.domain.menu.model.value_object;

import com.fight_world.mono.domain.menu.exception.MenuException;
import com.fight_world.mono.domain.menu.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
public class MenuDescription {

    @Column(name = "description", nullable = false)
    String value;

    public MenuDescription(String description) {
        if (value == null || value.isBlank() || value.length() > 50) {
            throw new MenuException(ExceptionMessage.MENU_DESCRIPTION_VALID);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuDescription menuDescription = (MenuDescription) o;

        return value.equals(menuDescription.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }
}
