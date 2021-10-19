package com.bairontapia.projects.cuidamed.mappings.bloodtype;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class BloodTypeConverter implements AttributeConverter<BloodType, Short> {

  @Override
  public Short convertToDatabaseColumn(final BloodType value) {
    Objects.requireNonNull(value);
    return (short) (value.ordinal() + 1);
  }

  @Override
  public BloodType convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return BloodType.getValueFromIndex(index);
  }
}
