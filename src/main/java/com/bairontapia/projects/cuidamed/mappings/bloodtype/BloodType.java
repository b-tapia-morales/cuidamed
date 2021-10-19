package com.bairontapia.projects.cuidamed.mappings.bloodtype;

public enum BloodType {
  A_MINUS("A-"),
  A_PLUS("A+"),
  B_MINUS("B-"),
  B_PLUS("B+"),
  AB_MINUS("AB-"),
  AB_PLUS("AB+"),
  O_MINUS("O-"),
  O_PLUS("O+");

  private static final BloodType[] VALUES = values();

  private final String name;

  BloodType(final String name) {
    this.name = name;
  }

  public static BloodType[] getValues() {
    return VALUES;
  }

  public static BloodType getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString() {
    return name;
  }

  public String getName() {
    return name;
  }
}
