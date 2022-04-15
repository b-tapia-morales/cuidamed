package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;

import java.util.Objects;

public record MedicalRecord(String rut, BloodType bloodType, HealthCare healthCare) {

    public static MedicalRecord createInstance(String rut, short bloodTypeCode,
                                               short healthCareCode) {
        return new MedicalRecord(rut, BloodType.getValueFromIndex(bloodTypeCode),
                HealthCare.getValueFromIndex(healthCareCode));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final MedicalRecord medicalRecord) {
            return Objects.equals(rut, medicalRecord.rut);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rut);
    }

    @Override
    public String toString() {
        return String.format
                ("""
                        Rut:\t\t\t\t\t\t\t\t\t%s
                        Grupo sanguíneo:\t\t\t%s
                        Sistema de salud:\t\t\t%s
                        """, RutUtils.format(rut), bloodType, healthCare);
    }

}
