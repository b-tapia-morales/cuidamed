package com.bairontapia.projects.cuidamed.localization;

public record Province(Short id, String name, Short regionId) {

  @Override
  public String toString() {
    return name;
  }
}
