package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medication.Medication;
import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescription;
import java.time.LocalDate;
import lombok.Getter;
import org.bson.types.ObjectId;

public class MedicationPrescriptionPOJO {

  @Getter
  private final ObjectId id;
  @Getter
  private final MedicationPOJO medication;
  @Getter
  private final LocalDate startDate;
  @Getter
  private final LocalDate endDate;
  @Getter
  private final Short quantity;

  public MedicationPrescriptionPOJO(final MedicationPrescription medicationPrescription,
      final Medication medication) {
    this.id = new ObjectId();
    this.medication = new MedicationPOJO(medication);
    this.startDate = medicationPrescription.startDate();
    this.endDate = medicationPrescription.endDate();
    this.quantity = medicationPrescription.quantity();
  }
}
