package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import lombok.Getter;

import java.time.LocalDate;

public class ElderPOJO {

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
    private final Gender gender;
    @Getter
    private final Boolean isActive;
    @Getter
    private final LocalDate admissionDate;
    @Getter
    private final ResponsiblePOJO responsible;
    @Getter
    private final MedicalRecordPOJO medicalRecord;

    public ElderPOJO(final Elder elder, final ResponsiblePOJO responsiblePOJO, final MedicalRecordPOJO medicalRecordPOJO) {
        rut = elder.rut();
        firstName = elder.firstName();
        lastName = elder.lastName();
        secondLastName = elder.secondLastName();
        birthDate = elder.birthDate();
        gender = elder.gender();
        age = elder.age();
        isActive = elder.isActive();
        admissionDate = elder.admissionDate();
        responsible = responsiblePOJO;
        medicalRecord = medicalRecordPOJO;
    }

}
