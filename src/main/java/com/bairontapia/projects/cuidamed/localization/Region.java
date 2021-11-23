package com.bairontapia.projects.cuidamed.localization;

public record Region(Short id, String name, String abbreviation, String capital) {

  @Override
  public String toString() {
    return name;
  }
}
