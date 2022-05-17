package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.prescription.Diagnostic;
import lombok.Getter;

import java.time.LocalDate;

public class DiagnosticPOJO {

    @Getter
    private final LocalDate date;
    @Getter
    private final DiseasePOJO disease;

    public DiagnosticPOJO(final Diagnostic diagnostic, final Disease disease) {
        this.date = diagnostic.prescriptionDate();
        this.disease = new DiseasePOJO(disease);
    }

}
