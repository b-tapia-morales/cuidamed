package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;

public record HealthySystemTypeStats(HealthCare healthCare, Integer frequency) {

  public static HealthySystemTypeStats createInstance(short healthCare, Integer frequency){
    return new HealthySystemTypeStats(HealthCare.getValueFromIndex(healthCare),frequency);
  }

}
