package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import java.time.LocalDate;
import java.util.Objects;

public record SurgicalIntervention(String rut, LocalDate interventionDate, String hospital,int levelSeverety,String description) {


  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final SurgicalIntervention surgicalIntervention) {
      return Objects.equals(rut, surgicalIntervention.rut);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(rut);
  }
}
