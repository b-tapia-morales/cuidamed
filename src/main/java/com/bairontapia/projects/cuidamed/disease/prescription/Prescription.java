package com.bairontapia.projects.cuidamed.disease.prescription;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Date;
import java.time.LocalDate;

public record Prescription(String rut, String diseaseName, LocalDate prescriptionDate,
                           String description) {

    public static Prescription createInstance(String rut, String diseaseName, Date prescriptionDate,
                                              String description) {
        return new Prescription(rut, diseaseName, prescriptionDate.toLocalDate(), description);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final Prescription prescription) {
            return new EqualsBuilder().
                    append(rut, prescription.rut).
                    append(diseaseName, prescription.diseaseName).
                    append(prescriptionDate, prescription.prescriptionDate).
                    isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(rut).
                append(diseaseName).
                append(prescriptionDate).
                toHashCode();
    }
}
