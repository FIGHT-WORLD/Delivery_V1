package com.fight_world.mono.domain.payment.model.value_object;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
public class PaymentTotalPrice {

    private BigDecimal value;

    public PaymentTotalPrice(BigDecimal value) {

        if (value. || value)

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentTotalPrice that = (PaymentTotalPrice) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(value);
    }
}
