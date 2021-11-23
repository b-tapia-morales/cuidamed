package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.time.LocalDate;
import java.util.Objects;

public record Carer(String rut, String firstName, String lastName, String secondLastName,
                    LocalDate birthDate, Gender gender, Integer mobilePhone, LocalDate hireDate) {

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Carer carer) {
      return Objects.equals(rut, carer.rut);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(rut);
  }

  @Override
  public String toString() {
    return
        String.format("""
                Rut:\t\t\t\t\t\t\t\t\t%s
                Nombres:\t\t\t\t\t\t\t%s
                Apellido paterno:\t\t\t%s
                Apellido materno:\t\t\t%s
                Fecha de nacimiento:\t%s
                Sexo:\t\t\t\t\t\t\t\t\t%s
                Teléfono móvil:\t\t\t\t%s
                Fecha de contrato:\t\t%s
                """, rut, firstName, lastName, secondLastName, birthDate, gender, mobilePhone,
            hireDate);
  }
}
