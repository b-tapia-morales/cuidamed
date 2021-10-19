package com.bairontapia.projects.cuidamed.mappings.healthsystem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class HealthSystemConverter implements AttributeConverter<HealthSystem, Short> {

  @Override
  public Short convertToDatabaseColumn(final HealthSystem value) {
    Objects.requireNonNull(value);
    return (short) (value.ordinal() + 1);
  }

  @Override
  public HealthSystem convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return HealthSystem.getValueFromIndex(index);
  }
}
