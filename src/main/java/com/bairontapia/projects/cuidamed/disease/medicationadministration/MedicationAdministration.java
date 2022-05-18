package com.bairontapia.projects.cuidamed.disease.medicationadministration;

import com.bairontapia.projects.cuidamed.mappings.dosagestatus.DosageStatus;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record MedicationAdministration(String rut, String medicationName,
                                       LocalDateTime estimatedDateTime,
                                       LocalDateTime realDatetime, DosageStatus status,
                                       String carerRut) {

  public static MedicationAdministration createInstance(String rut, String medicationName,
      LocalDateTime estimatedDateTime, LocalDateTime realDateTime, short dosageStatus,
      String carerRut) {
    if (realDateTime != null) {
      return new MedicationAdministration(rut, medicationName, estimatedDateTime, realDateTime,
          DosageStatus.getValueFromIndex(dosageStatus), carerRut);
    } else {
      return new MedicationAdministration(rut, medicationName, estimatedDateTime, null,
          DosageStatus.getValueFromIndex(dosageStatus), carerRut);
    }
  }

  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final MedicationAdministration medicationAdministration) {
      return new EqualsBuilder().
          append(rut, medicationAdministration.rut).
          append(medicationName, medicationAdministration.medicationName).
          append(estimatedDateTime, medicationAdministration.estimatedDateTime).
          isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
        append(rut).
        append(medicationName).
        append(estimatedDateTime).
        toHashCode();
  }
}
