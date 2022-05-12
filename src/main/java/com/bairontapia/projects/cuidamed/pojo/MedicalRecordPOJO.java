package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class MedicalRecordPOJO {

    @Getter
    private final BloodType bloodType;
    @Getter
    private final HealthCare healthCare;
    @Getter
    private final List<RoutineCheckupPOJO> routineCheckupPOJOS;

    public MedicalRecordPOJO(final MedicalRecord medicalRecord, final List<RoutineCheckup> routineCheckups) {
        bloodType = medicalRecord.bloodType();
        healthCare = medicalRecord.healthCare();
        routineCheckupPOJOS = routineCheckups.stream().map(RoutineCheckupPOJO::new).toList();
    }

}
