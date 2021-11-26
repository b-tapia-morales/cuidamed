package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;

public record BloodTypeStats(BloodType bloodType, Integer frequency) {

  public static BloodTypeStats createInstance(short bloodTypeCode, Integer frequency) {
    return new BloodTypeStats(BloodType.getValueFromIndex(bloodTypeCode), frequency);
  }

  @Override
  public String toString() {
    return String.format
        ("""
            Grupo sanguíneo:\t\t\t\t\t\t\t\t\t%s
            Cantidad:\t\t\t\t\t\t\t\t\t\t\t\t\t%s
            """, bloodType, frequency);
  }
}
