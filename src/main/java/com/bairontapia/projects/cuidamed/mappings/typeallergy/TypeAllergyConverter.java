package com.bairontapia.projects.cuidamed.mappings.typeallergy;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class TypeAllergyConverter implements AttributeConverter<TypeAllergy, Short> {

    @Override
    public Short convertToDatabaseColumn(TypeAllergy value) {
        Objects.requireNonNull(value);
        return (short) (value.ordinal() + 1);
    }

    @Override
    public TypeAllergy convertToEntityAttribute(Short index) {
        Objects.requireNonNull(index);
        return TypeAllergy.getValueFromIndex(index);
    }
}
