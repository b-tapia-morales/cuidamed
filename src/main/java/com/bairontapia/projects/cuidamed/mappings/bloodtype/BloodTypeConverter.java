package com.bairontapia.projects.cuidamed.mappings.bloodtype;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class BloodTypeConverter implements AttributeConverter<BloodType, Short> {

    @Override
    public Short convertToDatabaseColumn(BloodType value) {
        Objects.requireNonNull(value);
        return (short) value.getIndex();
    }

    @Override
    public BloodType convertToEntityAttribute(Short index) {
        Objects.requireNonNull(index);
        return BloodType.getValueFromIndex(index);
    }
}
