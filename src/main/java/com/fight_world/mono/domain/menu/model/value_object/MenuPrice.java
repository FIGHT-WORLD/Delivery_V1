package com.fight_world.mono.domain.menu.model.value_object;

import com.fight_world.mono.domain.menu.exception.MenuException;
import com.fight_world.mono.domain.menu.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class MenuPrice {

    @Column(name = "price", nullable = false)
    BigDecimal value;

    public MenuPrice(BigDecimal value) {

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new MenuException(ExceptionMessage.MENU_PRICE_VALID);
        }

        this.value = value;
    }
}
