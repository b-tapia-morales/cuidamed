package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record SurgicalIntervention(String rut, LocalDate interventionDate, String hospital,
                                   Short severity, String description) {

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final SurgicalIntervention surgicalIntervention) {
      return new EqualsBuilder()
          .append(rut, surgicalIntervention.rut)
          .append(interventionDate, surgicalIntervention.interventionDate)
          .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(rut)
        .append(interventionDate)
        .toHashCode();
  }
}
