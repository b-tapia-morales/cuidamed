package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "routine_checkup")
@Getter
@Setter
public class RoutineCheckup {
  @EmbeddedId
  @Setter(AccessLevel.PROTECTED)
  private RoutineCheckupId id;

  @Column(name = "height", nullable = false)
  private Double height;

  @Column(name = "weight", nullable = false)
  private Double weight;

  @Column(name = "bmi", nullable = false)
  private Double bmi;

  @Column(name = "heart_rate", nullable = false)
  private Short heartRate;

  @Column(name = "diastolic_pressure", nullable = false)
  private Double diastolicPressure;

  @Column(name = "systolic_pressure", nullable = false)
  private Double systolicPressure;

  @Column(name = "body_temperature", nullable = false)
  private Double bodyTemperature;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "elder_rut", insertable = false, nullable = false, updatable = false)
  @Setter(AccessLevel.PROTECTED)
  private MedicalRecord medicalRecord;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final RoutineCheckup routineCheckup) {
      return Objects.equals(id, routineCheckup.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
