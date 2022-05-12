package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescription;
import java.time.LocalDate;
import lombok.Getter;

public class MedicationPrescriptionPOJO {
  @Getter private final MedicationPOJO medication;
  @Getter private final Integer quantity;
  @Getter private final LocalDate startDate;
  @Getter private final LocalDate endDate;

  public MedicationPrescriptionPOJO(
      final MedicationPrescription medicationPrescription,
      final MedicationPOJO medicationPOJO,
      final Integer quantity) {
    medication = medicationPOJO;
    this.quantity = quantity;
    startDate = medicationPrescription.startDate();
    endDate = medicationPrescription.endDate();
  }
}
