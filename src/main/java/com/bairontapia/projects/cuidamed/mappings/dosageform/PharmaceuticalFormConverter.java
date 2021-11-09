package com.bairontapia.projects.cuidamed.mappings.dosageform;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PharmaceuticalFormConverter implements AttributeConverter<PharmaceuticalForm, Short> {

  @Override
  public Short convertToDatabaseColumn(final PharmaceuticalForm value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public PharmaceuticalForm convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return PharmaceuticalForm.getValueFromIndex(index);
  }
}
