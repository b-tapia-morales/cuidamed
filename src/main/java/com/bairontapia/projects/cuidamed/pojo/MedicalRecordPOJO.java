package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import lombok.Getter;

public class MedicalRecordPOJO {

    @Getter
    private final String bloodType;
    @Getter
    private final String healthCare;

    public MedicalRecordPOJO(final MedicalRecord medicalRecord) {
        this.bloodType = medicalRecord.bloodType().toString();
        this.healthCare = medicalRecord.healthCare().toString();
    }

    @Override
    public String toString() {
        return String.format
                ("""
                        Grupo sanguíneo:\t\t\t%s
                        Sistema de salud:\t\t\t%s
                        """, bloodType, healthCare);
    }

}
