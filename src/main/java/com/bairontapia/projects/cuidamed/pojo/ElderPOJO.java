package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public class ElderPOJO {

    @Getter
    private final ObjectId id;
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
    private final Boolean isActive;
    @Getter
    private final LocalDate admissionDate;
    @Getter
    private final ResponsiblePOJO responsible;
    @Getter
    private final MedicalRecordPOJO medicalRecord;
    @Getter
    private final List<DiagnosticPOJO> diagnostics;

    public ElderPOJO(final Elder elder, final ResponsiblePOJO responsiblePOJO, final MedicalRecordPOJO medicalRecordPOJO, final List<DiagnosticPOJO> diagnostics) {
        this.id = new ObjectId();
        this.rut = elder.rut();
        this.firstName = elder.firstName();
        this.lastName = elder.lastName();
        this.secondLastName = elder.secondLastName();
        this.birthDate = elder.birthDate();
        this.gender = elder.gender().toString();
        this.age = elder.age();
        this.isActive = elder.isActive();
        this.admissionDate = elder.admissionDate();
        this.responsible = responsiblePOJO;
        this.medicalRecord = medicalRecordPOJO;
        this.diagnostics = diagnostics;
    }

    @Override
    public String toString() {
        return String.format("""
                Rut:\t\t\t\t\t\t\t\t\t%s
                Nombre completo:\t\t\t%s
                Fecha de nacimiento:\t%s
                Edad:\t\t\t\t\t\t\t\t\t%s
                Sexo:\t\t\t\t\t\t\t\t\t%s
                Activo:\t\t\t\t\t\t\t\t%s
                Fecha de admisión:\t\t%s
                                                
                Ficha Médica:
                %s
                """, RutUtils.format(rut), StringUtils.joinWith(" ", firstName, lastName, secondLastName), birthDate, age, gender, isActive.equals(Boolean.TRUE) ? "Sí" : "No", admissionDate, medicalRecord);
    }
}
