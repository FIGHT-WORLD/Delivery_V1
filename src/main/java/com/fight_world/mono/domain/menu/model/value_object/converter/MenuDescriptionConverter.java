package com.fight_world.mono.domain.menu.model.value_object.converter;

import com.fight_world.mono.domain.menu.model.value_object.MenuDescription;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MenuDescriptionConverter implements AttributeConverter<MenuDescription, String> {

    @Override
    public String convertToDatabaseColumn(MenuDescription menuDescription) {

        if(menuDescription == null) {
            return null;
        }

        return menuDescription.getValue();
    }

    @Override
    public MenuDescription convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return new MenuDescription(dbData);
    }
}
