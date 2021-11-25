package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public record Elder(String rut, String firstName, String lastName, String secondLastName,
                    LocalDate birthDate, Integer age, Gender gender, Boolean isActive,
                    LocalDate admissionDate, String responsibleRut) {

  public static Elder createInstance(String rut, String firstName, String lastName,
      String secondLastName, Date birthDate, short genderCode, boolean isActive,
      Date admissionDate, String responsibleRut) {
    final var then = birthDate.toLocalDate();
    final var now = LocalDate.now();
    final var age = Period.between(then, now).getYears();
    return new Elder(rut, firstName, lastName, secondLastName, birthDate.toLocalDate(),
        age, Gender.getValueFromCode(genderCode), isActive, admissionDate.toLocalDate(),
        responsibleRut);
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
                Edad:\t\t\t\t\t\t\t\t\t%s
                Sexo:\t\t\t\t\t\t\t\t\t%s
                Activo:\t\t\t\t\t\t\t\t%s
                Fecha de admisión:\t\t%s
                """, RutUtils.format(rut),
            StringUtils.joinWith(" ", firstName, lastName, secondLastName), birthDate, age,
            gender, isActive ? "Sí" : "No", admissionDate);
  }
}
