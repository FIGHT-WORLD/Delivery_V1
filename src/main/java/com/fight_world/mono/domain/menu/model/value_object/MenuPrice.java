package com.fight_world.mono.domain.menu.model.value_object;

import com.fight_world.mono.domain.menu.exception.MenuException;
import com.fight_world.mono.domain.menu.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
public class MenuPrice {

    @Column(name = "price", nullable = false)
    BigDecimal value;

    public MenuPrice(BigDecimal value) {
        // TODO: if조건문 확인하기
        if (value == null) {
            throw new MenuException(ExceptionMessage.MENU_PRICE_VALID);
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

        MenuPrice menuPrice = (MenuPrice) o;

        return value.equals(menuPrice.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }
}
