package com.bairontapia.projects.cuidamed.mappings.dosage_status;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum DosageStatus {
  UNDEFINED("Sin definir"),
  PENDING("Pendiente"),
  UNFULFILLED("Incumplido"),
  FULFILLED("Cumplido");

  private final String status;
  private static final DosageStatus[] VALUES = values();

  DosageStatus(final String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return status;
  }

  public String getStatus() {
    return this.status;
  }

  public static DosageStatus[] getVALUES() {
    return VALUES;
  }

  /*
  VALUES[0] <-> 1
  VALUES[1] <-> 2
  VALUES[2] <-> 3
  ...
  VALUES[n] <-> n+1
   */
  public int getIndex() {
    return ordinal() + 1;
  }

  public static DosageStatus getValueFromIndex(final int index) {
    if (index < 1 | index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }
}
