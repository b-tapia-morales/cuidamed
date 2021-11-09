package com.bairontapia.projects.cuidamed.mappings.disesetype;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DiseaseTypeConverter implements AttributeConverter<DiseaseType, Short> {

  @Override
  public Short convertToDatabaseColumn(final DiseaseType value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public DiseaseType convertToEntityAttribute(final Short index) {
    Objects.requireNonNull(index);
    return DiseaseType.getValueFromIndex(index);
  }
}
