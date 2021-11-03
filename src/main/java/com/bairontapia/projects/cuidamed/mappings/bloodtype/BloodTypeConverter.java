package com.bairontapia.projects.cuidamed.mappings.bloodtype;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BloodTypeConverter implements AttributeConverter<BloodType, Short> {

  @Override
  public Short convertToDatabaseColumn(final BloodType value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public BloodType convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return BloodType.getValueFromIndex(index);
  }
}
