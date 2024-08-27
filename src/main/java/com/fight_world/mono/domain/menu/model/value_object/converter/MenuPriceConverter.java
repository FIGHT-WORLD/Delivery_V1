package com.fight_world.mono.domain.menu.model.value_object.converter;

import com.fight_world.mono.domain.menu.model.value_object.MenuPrice;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class MenuPriceConverter implements AttributeConverter<MenuPrice, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(MenuPrice menuPrice) {

        if(menuPrice == null) {
            return null;
        }

        return menuPrice.getValue();
    }

    @Override
    public MenuPrice convertToEntityAttribute(BigDecimal dbData) {

        if(dbData == null) {
            return null;
        }

        return new MenuPrice(dbData);
    }
}
