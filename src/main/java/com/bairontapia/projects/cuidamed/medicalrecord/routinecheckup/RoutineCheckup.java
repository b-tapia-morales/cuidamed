package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import java.time.LocalDate;
import java.util.Objects;


public record RoutineCheckup(String rut, LocalDate checkupDate, Double height, Double weight,
                              Double bmi,Short heartRate, Double distolicPressure, Double systolicPressure,
                             Double bodyTemperature) {


  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final RoutineCheckup routineCheckup) {
      return Objects.equals(rut, routineCheckup.rut);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(rut);
  }
}
