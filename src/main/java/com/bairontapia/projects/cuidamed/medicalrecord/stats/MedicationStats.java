package com.bairontapia.projects.cuidamed.medicalrecord.stats;

public record MedicationStats(String name, Integer frequency) {

  public static MedicationStats createInstance(String name, Integer frequency) {
    return new MedicationStats(name, frequency);
  }

}
