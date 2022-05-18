package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medicationadministration.MedicationAdministration;
import java.time.LocalDateTime;
import lombok.Getter;
import org.bson.types.ObjectId;

public class MedicationAdministrationPOJO {

  @Getter
  private final ObjectId id;
  @Getter
  private final LocalDateTime estimatedDateTime;
  @Getter
  private final LocalDateTime realDatetime;
  @Getter
  private final String status;

  public MedicationAdministrationPOJO(final MedicationAdministration medicationAdministration) {
    this.id = new ObjectId();
    this.estimatedDateTime = medicationAdministration.estimatedDateTime();
    this.realDatetime = medicationAdministration.realDatetime();
    this.status = medicationAdministration.status().toString();
  }

}
