package com.bairontapia.projects.cuidamed.mappings.healthcaresystem;

import lombok.Getter;

@Getter
public enum HealthCare {
  PUBLIC("Fonasa"),
  PRIVATE("Isapre");

  private static final HealthCare[] VALUES = values();

  private final String name;

  HealthCare(final String name) {
    this.name = name;
  }

  public static HealthCare[] getValues() {
    return VALUES;
  }

  public static HealthCare getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString() {
    return name;
  }

  public int getIndex() {
    return ordinal() + 1;
  }
}
