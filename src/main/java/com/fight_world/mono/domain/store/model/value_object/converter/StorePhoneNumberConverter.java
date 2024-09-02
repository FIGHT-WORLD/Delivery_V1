package com.fight_world.mono.domain.store.model.value_object.converter;

import com.fight_world.mono.domain.store.model.value_object.StorePhoneNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StorePhoneNumberConverter implements AttributeConverter<StorePhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(StorePhoneNumber storePhoneNumber) {

        if(storePhoneNumber == null) {
            return null;
        }

        return storePhoneNumber.getValue();
    }

    @Override
    public StorePhoneNumber convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        return new StorePhoneNumber(dbData);
    }
}
