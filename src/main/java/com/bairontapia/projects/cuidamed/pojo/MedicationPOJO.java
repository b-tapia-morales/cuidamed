package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medication.Medication;
import lombok.Getter;
import org.bson.types.ObjectId;

public class MedicationPOJO {

  @Getter private final ObjectId id;
  @Getter private final String name;
  @Getter private final String administrationRoute;
  @Getter private final String measureUnit;
  @Getter private final String pharmaceuticalForm;

  public MedicationPOJO(final Medication medication) {
    this.id = new ObjectId();
    this.name = medication.name();
    this.administrationRoute = medication.administrationRoute().toString();
    this.measureUnit = medication.measureUnit().toString();
    this.pharmaceuticalForm = medication.pharmaceuticalForm().toString();
  }
}
