package com.bairontapia.projects.cuidamed.medicalrecord;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "residence", name = "routine_checkup")
@Getter
@Setter
public class RoutineCheckup implements Serializable {
    @EmbeddedId
    private RoutineCheckupPK routineCheckupPK;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "bmi", nullable = false)
    private Double bmi;

    @Column(name = "heart_rate", nullable = false)
    private Integer heartRate;

    @Column(name = "diastolic_pressure", nullable = false)
    private Double diastolicPressure;

    @Column(name = "systolic_preassure", nullable = false)
    private Double systolicPreassure;

    @Column(name = "body_temperature", nullable = false)
    private Double bodyTemperature;

}
