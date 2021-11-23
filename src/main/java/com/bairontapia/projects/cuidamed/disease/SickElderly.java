package com.bairontapia.projects.cuidamed.disease;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record SickElderly(String rut, String diseaseName, LocalDate diagnosisDate) {

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final SickElderly sickElderly) {
      return new EqualsBuilder().
          append(rut,sickElderly.rut).
          append(diseaseName, sickElderly.diseaseName).
          append(diagnosisDate,sickElderly.diagnosisDate).
          isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
        append(rut).
        append(diseaseName).
        append(diagnosisDate).
        toHashCode();
  }

}
