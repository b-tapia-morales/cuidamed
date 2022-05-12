package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MedicalRecordPOJO {

    @Getter
    private final String bloodType;
    @Getter
    private final String healthCare;
    @Getter
    private final List<RoutineCheckupPOJO> routineCheckups;

    public MedicalRecordPOJO(final MedicalRecord medicalRecord, final List<RoutineCheckup> routineCheckups) {
        this.bloodType = medicalRecord.bloodType().toString();
        this.healthCare = medicalRecord.healthCare().toString();
        this.routineCheckups = routineCheckups.stream().map(RoutineCheckupPOJO::new).toList();
    }

    @Override
    public String toString() {
        return String.format
                ("""
                        Grupo sanguíneo:\t\t\t%s
                        Sistema de salud:\t\t\t%s
                                                
                        Chequeos rutinarios:
                        %s
                        """, bloodType, healthCare, StringUtils.join(routineCheckups, "\n"));
    }

}
