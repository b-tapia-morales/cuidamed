package com.bairontapia.projects.cuidamed.mappings.measureunit;

import lombok.Getter;

@Getter
public enum MeasureUnit {
  MG("Mg"),
  ML("Ml");

  private static final MeasureUnit[] VALUES = values();
  private final String value;

  MeasureUnit(final String value) {
    this.value = value;
  }

  public static MeasureUnit[] getValues() {
    return VALUES;
  }

  public static MeasureUnit getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  public int getIndex() {
    return ordinal() + 1;
  }
}
