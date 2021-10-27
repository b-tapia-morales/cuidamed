package com.bairontapia.projects.cuidamed.mappings.healthcaresystem;

import lombok.Getter;

@Getter
public enum HealthcareSystem {
  PUBLIC("Fonasa"),
  PRIVATE("Isapre");

  private static final HealthcareSystem[] VALUES = values();

  private final String name;

  HealthcareSystem(final String name) {
    this.name = name;
  }

  public static HealthcareSystem[] getValues() {
    return VALUES;
  }

  public static HealthcareSystem getValueFromIndex(final int index) {
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
