package com.bairontapia.projects.cuidamed.mappings.severity;

import lombok.Getter;

@Getter
public enum Severity {
  MILD("Leve"),
  NORMAL("Normal"),
  SEVERE("Severa");

  private static final Severity[] VALUES = values();

  private final String value;

  Severity(final String value) {
    this.value = value;
  }

  public static Severity[] getValues() {
    return VALUES;
  }

  public static Severity getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString() {
    return value;
  }

  public int getIndex() {
    return ordinal() + 1;
  }
}
