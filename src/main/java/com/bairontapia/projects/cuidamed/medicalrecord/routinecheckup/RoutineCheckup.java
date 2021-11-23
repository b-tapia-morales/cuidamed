package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public record RoutineCheckup(String rut, LocalDate checkupDate, Double height, Double weight,
                             Double bmi, Short heartRate, Double diastolicPressure,
                             Double systolicPressure, Double bodyTemperature) {

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final RoutineCheckup routineCheckup) {
      return new EqualsBuilder()
          .append(rut, routineCheckup.rut)
          .append(checkupDate, routineCheckup.checkupDate).isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(rut)
        .append(checkupDate).toHashCode();
  }
}
