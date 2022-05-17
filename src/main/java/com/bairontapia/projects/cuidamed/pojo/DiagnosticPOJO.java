package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.prescription.Diagnostic;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

public class DiagnosticPOJO {

  @Getter
  private final LocalDate date;
  @Getter
  private final DiseasePOJO disease;
  @Getter
  private final List<MedicationPrescriptionPOJO> medicationPrescriptions;

  public DiagnosticPOJO(final Diagnostic diagnostic, final Disease disease,
      final List<MedicationPrescriptionPOJO> medicationPrescriptions) {
    this.date = diagnostic.prescriptionDate();
    this.disease = new DiseasePOJO(disease);
    this.medicationPrescriptions = medicationPrescriptions;
  }
}
