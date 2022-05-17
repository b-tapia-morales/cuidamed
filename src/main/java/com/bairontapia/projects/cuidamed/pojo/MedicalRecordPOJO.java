package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import lombok.Getter;

import java.util.List;

public class MedicalRecordPOJO {

    @Getter
    private final String bloodType;
    @Getter
    private final String healthCare;
    @Getter
    private final List<AllergyPOJO> allergyPOJOS;

    public MedicalRecordPOJO(final MedicalRecord medicalRecord, final List<AllergyPOJO> allergies) {
        this.bloodType = medicalRecord.bloodType().toString();
        this.healthCare = medicalRecord.healthCare().toString();
        this.allergyPOJOS = allergies;
    }

    @Override
    public String toString() {
        return String.format("""
                Grupo sanguíneo:\t\t\t%s
                Sistema de salud:\t\t\t%s
                """, bloodType, healthCare);
    }

}
