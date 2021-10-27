package com.bairontapia.projects.cuidamed.mappings.healthcaresystem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class HealthCareSystemConverter implements AttributeConverter<HealthCareSystem, Short> {

  @Override
  public Short convertToDatabaseColumn(final HealthCareSystem value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public HealthCareSystem convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return HealthCareSystem.getValueFromIndex(index);
  }
}
