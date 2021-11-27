package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.sql.Date;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record SurgicalIntervention(String rut, LocalDate interventionDate, String hospital,
                                   Short severity, String description) {

  public static SurgicalIntervention createInstance(String rut, Date interventionDate,
      String hospital, short severity, String description) {
    return new SurgicalIntervention(rut, interventionDate.toLocalDate(), hospital, severity,
        description);
  }

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

  @Override
  public String toString() {
    return
        String.format("""
            Rut:\t\t\t\t\t\t\t\t\t\t\t%s
            Fecha de intervención:\t\t%s
            Hospital:\t\t\t\t\t\t\t\t\t%s
            Severidad:\t\t\t\t\t\t\t\t%s
            Descripción:\t\t\t\t\t\t\t%s
            """, RutUtils.format(rut), interventionDate, hospital, severity, description);
  }
}
