package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodTypeConverter;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCareSystem;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCareSystemConverter;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import com.bairontapia.projects.cuidamed.person.Elder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "residence", name = "medical_record")
@Getter
@Setter
public class MedicalRecord {
  @Id
  @Column(name = "elder_rut", unique = true, nullable = false, updatable = false)
  private String rut;

  @Column(name = "blood_type", nullable = false)
  @Convert(converter = BloodTypeConverter.class)
  private BloodType bloodType;

  @Column(name = "healthcare_system", nullable = false)
  @Convert(converter = HealthCareSystemConverter.class)
  private HealthCareSystem healthCareSystem;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "elder_rut", nullable = false, updatable = false)
  @Setter(AccessLevel.PROTECTED)
  private Elder elder;

  @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @OrderBy("checkup_date DESC")
  @Setter(AccessLevel.PRIVATE)
  private List<RoutineCheckup> listRoutineCheckup = new ArrayList<>();
}
