package com.bairontapia.projects.cuidamed.mappings.dosageform;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class DosageFormConverter implements AttributeConverter<DosageForm, Short> {

  @Override
  public Short convertToDatabaseColumn(final DosageForm value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public DosageForm convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return DosageForm.getValueFromIndex(index);
  }
}
