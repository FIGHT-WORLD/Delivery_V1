package com.fight_world.mono.domain.payment.model.value_object.converter;

import com.fight_world.mono.domain.payment.model.value_object.PaymentTotalPrice;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class PaymentTotalPriceConverter implements AttributeConverter<PaymentTotalPrice, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(PaymentTotalPrice paymentTotalPrice) {

        if (paymentTotalPrice == null) {
            return null;
        }

        return paymentTotalPrice.getValue();
    }

    @Override
    public PaymentTotalPrice convertToEntityAttribute(BigDecimal bigDecimal) {

        if (bigDecimal == null) {
            return null;
        }

        return new PaymentTotalPrice(bigDecimal);
    }
}
