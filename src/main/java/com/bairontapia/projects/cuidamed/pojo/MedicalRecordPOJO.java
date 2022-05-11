package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import lombok.Getter;

import java.util.List;

public class MedicalRecordPOJO {

    @Getter
    private final BloodType bloodType;
    @Getter
    private final HealthCare healthCare;
    @Getter
    private final List<RoutineCheckupPOJO> routineCheckups;

    public MedicalRecordPOJO(final MedicalRecord medicalRecord, final List<RoutineCheckupPOJO> routineCheckupPOJOS) {
        bloodType = medicalRecord.bloodType();
        healthCare = medicalRecord.healthCare();
        routineCheckups = routineCheckupPOJOS;
    }

}
