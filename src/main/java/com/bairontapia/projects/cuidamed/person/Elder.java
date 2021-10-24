package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.medical_record.MedicalRecord;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "residence", name = "elder")
@PrimaryKeyJoinColumn(name = "rut")
@Getter
@Setter
public class Elder extends Person {
  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "admission_date", nullable = false)
  private LocalDate admissionDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "responsible_rut",
      referencedColumnName = "rut",
      updatable = false,
      nullable = false)
  private Responsible responsible;

  //
  @OneToOne(mappedBy = "elder")
  private MedicalRecord medicalRecord;
  //
}
