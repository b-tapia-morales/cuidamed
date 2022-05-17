package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.person.responsible.Responsible;
import lombok.Getter;

import java.time.LocalDate;

public class ResponsiblePOJO {

    @Getter
    private final String rut;
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
    @Getter
    private final String secondLastName;
    @Getter
    private final LocalDate birthDate;
    @Getter
    private final Integer age;
    @Getter
    private final String gender;
    @Getter
    private final Integer mobilePhone;

    public ResponsiblePOJO(final Responsible responsible) {
        rut = responsible.rut();
        firstName = responsible.firstName();
        lastName = responsible.lastName();
        secondLastName = responsible.secondLastName();
        birthDate = responsible.birthDate();
        gender = responsible.gender().toString();
        age = responsible.age();
        mobilePhone = responsible.mobilePhone();
    }

}
