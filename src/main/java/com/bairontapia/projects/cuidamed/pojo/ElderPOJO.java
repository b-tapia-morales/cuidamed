package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Getter
    private final List<PrescriptionPOJO> prescriptions;

    public ElderPOJO(final Elder elder, final ResponsiblePOJO responsiblePOJO,
                     final MedicalRecordPOJO medicalRecordPOJO, final List<PrescriptionPOJO> prescriptions) {
        this.rut = elder.rut();
        this.firstName = elder.firstName();
        this.lastName = elder.lastName();
        this.secondLastName = elder.secondLastName();
        this.birthDate = elder.birthDate();
        this.gender = elder.gender();
        this.age = elder.age();
        this.isActive = elder.isActive();
        this.admissionDate = elder.admissionDate();
        this.responsible = responsiblePOJO;
        this.medicalRecord = medicalRecordPOJO;
        this.prescriptions = prescriptions;
    }

}
