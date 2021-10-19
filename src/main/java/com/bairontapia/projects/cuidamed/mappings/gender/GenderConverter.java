package com.bairontapia.projects.cuidamed.mappings.gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    public Integer convertToDatabaseColumn(final Gender value) {
        Objects.requireNonNull(value);
        return value.getCode();
    }

    public Gender convertToEntityAttribute(final Integer code) {
        Objects.requireNonNull(code);
        return Gender.getValueFromCode(code);
    }

}
