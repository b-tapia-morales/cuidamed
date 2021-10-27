package com.bairontapia.projects.cuidamed.mappings.healthcaresystem;

import lombok.Getter;

@Getter
public enum HealthCareSystem {
  PUBLIC("Fonasa"),
  PRIVATE("Isapre");

  private static final HealthCareSystem[] VALUES = values();

  private final String name;

  HealthCareSystem(final String name) {
    this.name = name;
  }

  public static HealthCareSystem[] getValues() {
    return VALUES;
  }

  public static HealthCareSystem getValueFromIndex(final int index) {
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
