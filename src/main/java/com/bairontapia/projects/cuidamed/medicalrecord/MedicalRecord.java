package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCareSystem;
import java.util.Objects;



public record MedicalRecord(String rut, BloodType bloodType, HealthCareSystem healthCareSystem) {

  //lista de routinechcku
  //lista de  surgigalIntervention

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final MedicalRecord medicalRecord) {
      return Objects.equals(rut, medicalRecord.rut);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(rut);
  }

}
