package com.bairontapia.projects.cuidamed.disease;


import com.bairontapia.projects.cuidamed.mappings.diseasetype.DiseaseType;
import java.util.Objects;

public record Disease(String disease_name, DiseaseType diseaseType, Boolean is_chronic) {

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Disease disease) {
      return Objects.equals(disease_name, disease.disease_name);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(disease_name);
  }
}
