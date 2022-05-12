package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.prescription.Prescription;
import lombok.Getter;

import java.time.LocalDate;

public class PrescriptionPOJO {

  @Getter
  private final LocalDate date;
  @Getter
  private final DiseasePOJO disease;

  public PrescriptionPOJO(final Prescription prescription, final Disease disease) {
    this.date = prescription.prescriptionDate();
    this.disease = new DiseasePOJO(disease);
  }

}
