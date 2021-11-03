package com.bairontapia.projects.cuidamed.mappings.gender;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Short> {

  public Short convertToDatabaseColumn(final Gender value) {
    Objects.requireNonNull(value);
    return (short) value.getCode();
  }

  public Gender convertToEntityAttribute(final Short code) {
    Objects.requireNonNull(code);
    return Gender.getValueFromCode(code);
  }
}
