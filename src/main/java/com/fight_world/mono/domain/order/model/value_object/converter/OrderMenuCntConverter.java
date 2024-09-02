package com.fight_world.mono.domain.order.model.value_object.converter;

import com.fight_world.mono.domain.order.model.value_object.OrderMenuCnt;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderMenuCntConverter implements AttributeConverter<OrderMenuCnt, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderMenuCnt orderMenuCnt) {

        if (orderMenuCnt == null) {
            return null;
        }

        return orderMenuCnt.getValue();
    }

    @Override
    public OrderMenuCnt convertToEntityAttribute(Integer dbData) {

        if (dbData == null) {
            return null;
        }

        return new OrderMenuCnt(dbData);
    }
}
