package com.bairontapia.projects.cuidamed.disease.prescription;

import java.sql.Date;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Diagnostic(String rut, String diseaseName, LocalDate prescriptionDate,
                         String description) {

  public static Diagnostic createInstance(String rut, String diseaseName, Date prescriptionDate,
      String description) {
    return new Diagnostic(rut, diseaseName, prescriptionDate.toLocalDate(), description);
  }

  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Diagnostic prescription) {
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
