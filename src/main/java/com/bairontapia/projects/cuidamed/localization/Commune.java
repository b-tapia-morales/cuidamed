package com.bairontapia.projects.cuidamed.localization;

import java.util.Objects;

public record Commune(String regionName, String provinceName, Short id, String name) {

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Commune commune) {
      return Objects.equals(id, commune.id);
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
