package com.bairontapia.projects.cuidamed.mappings.allergytype;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class AllergyTypeConverter implements AttributeConverter<AllergyType, Short> {

    @Override
    public Short convertToDatabaseColumn(AllergyType value) {
        Objects.requireNonNull(value);
        return (short) (value.ordinal() + 1);
    }

    @Override
    public AllergyType convertToEntityAttribute(Short index) {
        Objects.requireNonNull(index);
        return AllergyType.getValueFromIndex(index);
    }
}
