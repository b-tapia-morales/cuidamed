package com.bairontapia.projects.cuidamed.localization;

public record Commune(Short id, String name, Short provinceId) {

  @Override
  public String toString() {
    return name;
  }
}
