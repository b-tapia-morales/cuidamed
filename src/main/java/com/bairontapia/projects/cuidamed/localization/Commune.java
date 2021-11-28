package com.bairontapia.projects.cuidamed.localization;

import java.util.Objects;

public record Commune(Short id, String name, Short provinceId) {

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
