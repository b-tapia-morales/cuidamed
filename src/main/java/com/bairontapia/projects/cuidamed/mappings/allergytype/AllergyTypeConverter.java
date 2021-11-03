package com.bairontapia.projects.cuidamed.mappings.allergytype;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AllergyTypeConverter implements AttributeConverter<AllergyType, Short> {

  @Override
  public Short convertToDatabaseColumn(AllergyType value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public AllergyType convertToEntityAttribute(Short index) {
    Objects.requireNonNull(index);
    return AllergyType.getValueFromIndex(index);
  }
}
