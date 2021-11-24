package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCareSystem;
import java.util.Objects;

public record MedicalRecord(String rut, BloodType bloodType, HealthCareSystem healthCareSystem) {

  public static MedicalRecord createInstance(String rut, short bloodTypeCode,
      short healthCareSystemCode) {
    return new MedicalRecord(rut, BloodType.getValueFromIndex(bloodTypeCode),
        HealthCareSystem.getValueFromIndex(healthCareSystemCode));
  }

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
