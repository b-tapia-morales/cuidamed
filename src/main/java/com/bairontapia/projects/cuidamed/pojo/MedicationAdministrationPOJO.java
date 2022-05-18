package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medicationadministration.MedicationAdministration;
import java.time.LocalDateTime;
import lombok.Getter;

public class MedicationAdministrationPOJO {

  @Getter
  private final LocalDateTime estimatedDateTime;
  @Getter
  private final LocalDateTime realDatetime;
  @Getter
  private final String status;

  public MedicationAdministrationPOJO(final MedicationAdministration medicationAdministration) {
    this.estimatedDateTime = medicationAdministration.estimatedDateTime();
    this.realDatetime = medicationAdministration.estimatedDateTime();
    this.status = medicationAdministration.status().toString();
  }

}
