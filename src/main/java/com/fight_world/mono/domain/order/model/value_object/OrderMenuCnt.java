package com.fight_world.mono.domain.order.model.value_object;

import com.fight_world.mono.domain.order.exception.OrderException;
import com.fight_world.mono.domain.order.message.ExceptionMessage;
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
            throw new OrderException(ExceptionMessage.ORDER_MENU_CNT_MIN);
        }

        this.value = value;
    }
}
