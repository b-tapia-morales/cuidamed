package com.bairontapia.projects.cuidamed.mappings.gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    public String convertToDatabaseColumn(final Gender value) {
        Objects.requireNonNull(value);
        return value.getName();
    }

    public Gender convertToEntityAttribute(final String name) {
        Objects.requireNonNull(name);
        return Gender.getValueFromName(name);
    }

}
