package com.bairontapia.projects.cuidamed.medicalrecord;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class RoutineCheckupId implements Serializable {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "elder_rut", nullable = false, updatable = false)
  private MedicalRecord medicalRecord;

  @Column(name = "checkup_date", nullable = false, updatable = false)
  private LocalDate checkupDate;
}
