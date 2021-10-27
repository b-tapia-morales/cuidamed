package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodTypeConverter;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthcareSystem;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthcareSystemConverter;
import com.bairontapia.projects.cuidamed.person.Elder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "residence", name = "medical_record")
@Getter
@Setter
public class MedicalRecord {
  @Id
  @Column(name = "elder_rut", unique = true, nullable = false, updatable = false)
  private String elderRut;

  @Column(name = "blood_type", nullable = false)
  @Convert(converter = BloodTypeConverter.class)
  private BloodType bloodType;

  @Column(name = "healthcare_system", nullable = false)
  @Convert(converter = HealthcareSystemConverter.class)
  private HealthcareSystem healthcareSystem;

  @OneToOne(cascade = CascadeType.ALL)
  @MapsId
  @JoinColumn(name = "elder_rut", updatable = false, nullable = false)
  @Setter(AccessLevel.PROTECTED)
  private Elder elder;
}
