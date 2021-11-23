package com.bairontapia.projects.cuidamed.mappings.administrationroute;

public enum AdministrationRoute {
  ORAL("Vía Oral"),
  PARENTAL("Vía Parental");

  private static final AdministrationRoute[] VALUES = values();
  private final String route;

  AdministrationRoute(final String route) {
    this.route = route;
  }

  public static AdministrationRoute[] getValues() {
    return VALUES;
  }

  public static AdministrationRoute getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString() {
    return route;
  }

  public int getIndex() {
    return ordinal() + 1;
  }
}
