package com.bairontapia.projects.cuidamed.disease;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Prescription(String rut, String diseaseName, LocalDate prescriptionDate,
                           String description) {

  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Prescription prescription) {
      return new EqualsBuilder().
          append(rut, prescription.rut).
          append(diseaseName, prescription.diseaseName).
          append(prescriptionDate, prescription.prescriptionDate).
          isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
        append(rut).
        append(diseaseName).
        append(prescriptionDate).
        toHashCode();
  }
}
