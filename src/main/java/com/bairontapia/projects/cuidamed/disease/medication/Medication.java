package com.bairontapia.projects.cuidamed.disease.medication;

import com.bairontapia.projects.cuidamed.mappings.administrationroute.AdministrationRoute;
import com.bairontapia.projects.cuidamed.mappings.measureunit.MeasureUnit;
import com.bairontapia.projects.cuidamed.mappings.pharmaceuticalform.PharmaceuticalForm;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Medication(String nameMedication, AdministrationRoute administrationRoute,
                         PharmaceuticalForm pharmaceuticalForm, MeasureUnit measureUnit) {

    public static Medication createInstance(String nameMedication, short administrationRoute,
                                            short pharmaceuticalForm, short measureUnit) {
        return new Medication(nameMedication,
                AdministrationRoute.getValueFromIndex(administrationRoute),
                PharmaceuticalForm.getValueFromIndex(pharmaceuticalForm),
                MeasureUnit.getValueFromIndex(measureUnit));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final Medication medication) {
            return new EqualsBuilder().
                    append(medication, medication.nameMedication).
                    isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(nameMedication).
                toHashCode();
    }

    @Override
    public String toString() {
        return nameMedication;
    }
}
