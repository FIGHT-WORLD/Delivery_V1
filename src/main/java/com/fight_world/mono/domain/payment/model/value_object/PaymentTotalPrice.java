package com.fight_world.mono.domain.payment.model.value_object;

import com.fight_world.mono.domain.payment.exception.PaymentException;
import com.fight_world.mono.domain.payment.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentTotalPrice {

    @Column(name = "total_price", nullable = false, updatable = false)
    private BigDecimal value;

    public PaymentTotalPrice(final BigDecimal value) {

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new PaymentException(ExceptionMessage.PAYMENT_NEED_BIGGER_THAN_ZERO);
        }

        this.value = value;
    }
}
