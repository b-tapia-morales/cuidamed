package com.bairontapia.projects.cuidamed.localization;

import java.util.Objects;

public record Province(String regionName, Short id, String name) {

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Province province) {
      return Objects.equals(id, province.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return name;
  }
}
