package com.fight_world.mono.domain.order_menu.model.value_object;

import com.fight_world.mono.domain.order_menu.exception.OrderMenuException;
import com.fight_world.mono.domain.order_menu.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderMenuCnt {

    @Column(name = "cnt", nullable = false)
    private Integer value;

    public OrderMenuCnt(Integer value) {

        if (value == null || value < 1) {
            throw new OrderMenuException(ExceptionMessage.ORDER_MENU_CNT_MIN);
        }

        this.value = value;
    }
}
