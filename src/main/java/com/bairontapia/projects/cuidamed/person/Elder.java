package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public record Elder(String rut, String fullName, LocalDate birthDate, Gender gender,
                    Boolean isActive, LocalDate admissionDate,
                    String responsibleRut) {

  public static Elder createInstance(String rut, String fullName, Date birthDate, short genderCode,
      boolean isActive, Date admissionDate, String responsibleRut) {
    return new Elder(rut, fullName, birthDate.toLocalDate(), Gender.getValueFromCode(genderCode),
        isActive, admissionDate.toLocalDate(), responsibleRut);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Elder elder) {
      return Objects.equals(rut, elder.rut);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(rut);
  }

  @Override
  public String toString() {
    return String.format
        ("""
            Rut:\t\t\t\t\t\t\t\t\t%s
            Nombre completo:\t\t\t%s
            Fecha de nacimiento:\t%s
            Sexo:\t\t\t\t\t\t\t\t\t%s
            Activo:\t\t\t\t\t\t\t\t\t%s
            Fecha de admisión:\t\t%s
            """, rut, fullName, birthDate, gender, isActive ? "Sí" : "No", admissionDate);
  }
}
