package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescription;
import com.bairontapia.projects.cuidamed.disease.prescription.Prescription;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

public class PrescriptionPOJO {
  @Getter private final DiseasePOJO disease;
  @Getter private final LocalDate prescriptionDate;
  @Getter private final String description;
  @Getter private final List<MedicationPrescriptionPOJO> medicationPrescriptions;

  public PrescriptionPOJO(
      final Prescription prescription,
      final DiseasePOJO diseasePOJO,
      final List<MedicationPrescription> medicationPrescriptions) {
    disease = diseasePOJO;
    prescriptionDate = prescription.prescriptionDate();
    description = prescription.description();
    this.medicationPrescriptions =
        medicationPrescriptions.stream().map(MedicationPrescriptionPOJO::new).toList();
  }
}
