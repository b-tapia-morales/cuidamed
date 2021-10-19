package com.bairontapia.projects.cuidamed.mappings.healthsystem;

public enum HealthSystem {
  PUBLIC("Fonasa"),
  PRIVATE("Isapre");

  private static final HealthSystem[] VALUES = values();

  private final String name;

  HealthSystem(final String name) {
    this.name = name;
  }

  public static HealthSystem[] getValues() {
    return VALUES;
  }

  public static HealthSystem getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString() {
    return name;
  }

  public String getStatus() {
    return name;
  }
}
