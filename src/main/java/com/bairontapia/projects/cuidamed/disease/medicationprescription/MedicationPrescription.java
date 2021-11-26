package com.bairontapia.projects.cuidamed.disease.medicationprescription;

import java.sql.Date;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record MedicationPrescription(String rut, String diseaseName, LocalDate prescriptionDate,
                                     String medicationName, LocalDate startDate, LocalDate endDate,
                                     Integer frequency, Integer quantity) {

  public static MedicationPrescription createInstance(String rut, String diseaseName,
      Date prescriptionDate, String medicationName, Date startDate, Date endDate, int frequency,
      int quantity) {
    return new MedicationPrescription(rut, diseaseName, prescriptionDate.toLocalDate(),
        medicationName, startDate.toLocalDate(), endDate.toLocalDate(), frequency, quantity);
  }

  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final MedicationPrescription medicationPrescription) {
      return new EqualsBuilder().
          append(rut, medicationPrescription.rut).
          append(diseaseName, medicationPrescription.diseaseName).
          append(prescriptionDate, medicationPrescription.prescriptionDate).
          append(medicationName, medicationPrescription.medicationName).
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
        append(medicationName).
        toHashCode();
  }

}
