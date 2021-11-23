package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.time.LocalDate;
import java.util.Objects;

public record Elder(String rut, String firstName, String lastName, String secondLastName,
                    LocalDate birthDate, Gender gender, Boolean isActive, LocalDate admissionDate,
                    String responsibleRut) {

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
                Nombres:\t\t\t\t\t\t\t%s
                Apellido paterno:\t\t\t%s
                Apellido materno:\t\t\t%s
                Fecha de nacimiento:\t%s
                Sexo:\t\t\t\t\t\t\t\t\t%s
                Activo:\t\t\t\t\t\t\t\t\t%s
                Fecha de admisión:\t\t%s
                """, rut, firstName, lastName, secondLastName, birthDate, gender,
            isActive ? "Sí" : "No", admissionDate);
  }
}
