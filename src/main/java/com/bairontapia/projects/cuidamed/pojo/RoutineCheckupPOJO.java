package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import lombok.Getter;
import org.apache.commons.math3.util.Precision;

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
        height = Precision.round(routineCheckup.height(), 2);
        weight = Precision.round(routineCheckup.weight(), 1);
        bmi = Precision.round(routineCheckup.bmi(), 2);
        heartRate = routineCheckup.heartRate();
        diastolicPressure = routineCheckup.diastolicPressure();
        systolicPressure = routineCheckup.systolicPressure();
        bodyTemperature = Precision.round(routineCheckup.bodyTemperature(), 1);
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
