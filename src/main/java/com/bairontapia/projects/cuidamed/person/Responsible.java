package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public record Responsible(String rut, String firstName, String lastName, String secondLastName,
                          LocalDate birthDate, Gender gender, Integer mobilePhone) {

  public static Responsible createInstance(String rut, String firstName, String lastName,
      String secondLastName, Date birthDate, short genderCode, int mobilePhone) {
    return new Responsible(rut, firstName, lastName, secondLastName, birthDate.toLocalDate(),
        Gender.getValueFromCode(genderCode), mobilePhone);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Responsible responsible) {
      return Objects.equals(rut, responsible.rut);
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
                Nombre completo:\t\t\t%s
                Fecha de nacimiento:\t%s
                Sexo:\t\t\t\t\t\t\t\t\t%s
                Teléfono móvil:\t\t\t\t+56 9 %s
                """, RutUtils.format(rut),
            StringUtils.joinWith(" ", firstName, lastName, secondLastName), birthDate, gender,
            mobilePhone);
  }

}
