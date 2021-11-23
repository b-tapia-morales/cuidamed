package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.time.LocalDate;
import java.util.Objects;

public record Responsible(String rut, String firstName, String lastName, String secondLastName,
                          LocalDate birthDate, Gender gender, Integer mobilePhone) {

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
                        
            Teléfono móvil:\t\t\t\t%s
            """, mobilePhone);
  }

}
