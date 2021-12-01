package com.bairontapia.projects.cuidamed.disease;


import com.bairontapia.projects.cuidamed.mappings.diseasetype.DiseaseType;
import java.util.Objects;


public record Disease(String disease_name, DiseaseType diseaseType, Boolean is_chronic) {


  public static Disease createInstance(String diseaseName, short diseaseType, boolean isChronic) {
    return new Disease(diseaseName, DiseaseType.getValueFromIndex(diseaseType), isChronic);
  }

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

  @Override
  public String toString() {
    return disease_name;
  }
}
