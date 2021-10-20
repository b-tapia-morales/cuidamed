package com.bairontapia.projects.cuidamed.mappings.dosagestatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class DosageStatusConverter implements AttributeConverter<DosageStatus, Short> {

  @Override
  public Short convertToDatabaseColumn(DosageStatus value) {
    Objects.requireNonNull(value);
    return (short) value.getIndex();
  }

  @Override
  public DosageStatus convertToEntityAttribute(Short index) {
    Objects.requireNonNull(index);
    return DosageStatus.getValueFromIndex(index);
  }
}
