package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Date;
import java.time.LocalDate;


public record RoutineCheckup(String rut, LocalDate checkupDate, Double height, Double weight,
                             Double bmi, Integer heartRate, Integer diastolicPressure,
                             Integer systolicPressure, Double bodyTemperature) {

    public static RoutineCheckup createInstance(String rut, Date checkupDate, Double height, Double weight, Double bmi,
                                                Integer heartRate, Integer diastolicPressure, Integer systolicPressure,
                                                Double bodyTemperature) {
        return new RoutineCheckup(rut, checkupDate.toLocalDate(), height, weight, bmi, heartRate,
                diastolicPressure, systolicPressure, bodyTemperature);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final RoutineCheckup routineCheckup) {
            return new EqualsBuilder()
                    .append(rut, routineCheckup.rut)
                    .append(checkupDate, routineCheckup.checkupDate).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(rut)
                .append(checkupDate).toHashCode();
    }

    @Override
    public String toString() {
        return String.format
                ("""
                                Rut:\t\t\t\t\t\t\t\t\t%s
                                Fecha del chequeo:\t\t%s
                                Altura:\t\t\t\t\t\t\t\t%s
                                Peso:\t\t\t\t\t\t\t\t\t%s
                                IMC:\t\t\t\t\t\t\t\t\t%s
                                Ritmo cardiaco:\t\t\t\t%s
                                Presión diastólica:\t\t%s
                                Presión sistólica:\t\t%s
                                Temperatura corporal:\t%s
                                """, RutUtils.format(rut),
                        checkupDate, height, weight, bmi, heartRate, diastolicPressure, systolicPressure,
                        bodyTemperature);
    }
}
