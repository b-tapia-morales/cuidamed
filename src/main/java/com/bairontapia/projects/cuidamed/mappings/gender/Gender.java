package com.bairontapia.projects.cuidamed.mappings.gender;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Gender {
  NOT_KNOWN(0, "Desconocido"),
  MALE(1, "Hombre"),
  FEMALE(2, "Mujer"),
  NOT_APPLICABLE(9, "No aplica");

  private static final Gender[] VALUES = values();
  private static final Map<Integer, Gender> VALUES_MAP =
      Stream.of(VALUES)
          .collect(
              Collectors.toMap(
                  Gender::getCode, Function.identity(), (a, b) -> a, LinkedHashMap::new));

  private final int code;
  private final String name;

  Gender(final int code, final String name) {
    this.code = code;
    this.name = name;
  }

  public static Gender[] getValues() {
    return VALUES;
  }

  public static Gender getValueFromCode(final int code) {
    if (!VALUES_MAP.containsKey(code)) {
      throw new IllegalArgumentException();
    }
    return VALUES_MAP.get(code);
  }

  @Override
  public String toString() {
    return name;
  }

  public int getIndex() {
    return ordinal() + 1;
  }
}
