package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.time.LocalDate;

public record Carer(String rut, String firstName, String lastName, String secondLastName,
                    LocalDate birthDate, Gender gender, Integer mobilePhone, LocalDate hireDate) {

  @Override
  public String toString() {
    return
        String.format("""
                        
            Teléfono móvil:\t\t\t\t%s
            Fecha de contrato:\t\t%s
            """, mobilePhone, hireDate);
  }
}
