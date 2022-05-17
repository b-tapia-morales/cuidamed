package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescription;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class MedicationPrescriptionPOJO {

  @Getter private final ObjectId id;
  @Getter private final LocalDate startDate;
  @Getter private final LocalDate endDate;
  @Getter private final Short quantity;

  public MedicationPrescriptionPOJO(final MedicationPrescription medicationPrescription) {
    this.id = new ObjectId();
    this.startDate = medicationPrescription.startDate();
    this.endDate = medicationPrescription.endDate();
    this.quantity = medicationPrescription.quantity();
  }
}
