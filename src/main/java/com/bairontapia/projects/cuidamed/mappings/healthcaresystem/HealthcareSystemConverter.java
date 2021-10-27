package com.bairontapia.projects.cuidamed.mappings.healthcaresystem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class HealthcareSystemConverter implements AttributeConverter<HealthcareSystem, Short> {

  @Override
  public Short convertToDatabaseColumn(final HealthcareSystem value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public HealthcareSystem convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return HealthcareSystem.getValueFromIndex(index);
  }
}
