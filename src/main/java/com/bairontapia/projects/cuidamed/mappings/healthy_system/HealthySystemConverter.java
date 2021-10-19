package com.bairontapia.projects.cuidamed.mappings.healthy_system;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class HealthySystemConverter implements AttributeConverter<HealthySystem, Short> {


    @Override
    public Short convertToDatabaseColumn(final HealthySystem value) {
        Objects.requireNonNull(value);
        return (short) (value.ordinal() + 1);
    }

    @Override
    public HealthySystem convertToEntityAttribute(final Short index) {
        Objects.requireNonNull(index);
        return HealthySystem.getValueFromIndex(index);
    }
}
