package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import lombok.Getter;

import java.time.LocalDate;

public class RoutineCheckupPOJO {

    @Getter
    private final LocalDate checkupDate;
    @Getter
    private final Double height;
    @Getter
    private final Double weight;
    @Getter
    private final Double bmi;
    @Getter
    private final Integer heartRate;
    @Getter
    private final Integer diastolicPressure;
    @Getter
    private final Integer systolicPressure;
    @Getter
    private final Double bodyTemperature;

    public RoutineCheckupPOJO(final RoutineCheckup routineCheckup) {
        checkupDate = routineCheckup.checkupDate();
        height = routineCheckup.height();
        weight = routineCheckup.weight();
        bmi = routineCheckup.bmi();
        heartRate = routineCheckup.heartRate();
        diastolicPressure = routineCheckup.diastolicPressure();
        systolicPressure = routineCheckup.systolicPressure();
        bodyTemperature = routineCheckup.bodyTemperature();
    }

    @Override
    public String toString() {
        return String.format
                ("""
                                Fecha del chequeo:\t\t%s
                                Altura:\t\t\t\t\t\t\t\t%s
                                Peso:\t\t\t\t\t\t\t\t\t%s
                                IMC:\t\t\t\t\t\t\t\t\t%s
                                Ritmo cardiaco:\t\t\t\t%s
                                Presión diastólica:\t\t%s
                                Presión sistólica:\t\t%s
                                Temperatura corporal:\t%s
                                """,
                        checkupDate, height, weight, bmi, heartRate, diastolicPressure, systolicPressure,
                        bodyTemperature);
    }

}
