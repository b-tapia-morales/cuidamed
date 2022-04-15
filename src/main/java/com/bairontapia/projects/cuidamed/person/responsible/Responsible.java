package com.bairontapia.projects.cuidamed.person.responsible;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.Person;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public record Responsible(String rut, String firstName, String lastName, String secondLastName,
                          LocalDate birthDate, Integer age, Gender gender,
                          Integer mobilePhone) implements Person {

    public static Responsible createInstance(String rut, String firstName, String lastName,
                                             String secondLastName, Date birthDate, short genderCode, int mobilePhone) {
        final var then = birthDate.toLocalDate();
        final var now = LocalDate.now();
        final var age = Period.between(then, now).getYears();
        return new Responsible(rut, firstName, lastName, secondLastName, birthDate.toLocalDate(),
                age, Gender.getValueFromIndex(genderCode), mobilePhone);
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
                                Edad:\t\t\t\t\t\t\t\t\t%s
                                Sexo:\t\t\t\t\t\t\t\t\t%s
                                Teléfono móvil:\t\t\t\t+56 9 %s
                                """, RutUtils.format(rut),
                        StringUtils.joinWith(" ", firstName, lastName, secondLastName), birthDate, age,
                        gender, mobilePhone);
    }

}
