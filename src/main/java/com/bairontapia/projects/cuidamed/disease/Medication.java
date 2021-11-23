package com.bairontapia.projects.cuidamed.disease;

import com.bairontapia.projects.cuidamed.mappings.administrationroute.AdministrationRoute;
import com.bairontapia.projects.cuidamed.mappings.dosageform.PharmaceuticalForm;
import com.bairontapia.projects.cuidamed.mappings.measureunit.MeasureUnit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Medication(String medication, AdministrationRoute administrationRoute,
                         PharmaceuticalForm pharmaceuticalForm, MeasureUnit measureUnit) {

  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Medication medication) {
      return new EqualsBuilder().
          append(medication,medication.medication).
          isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
        append(medication).
        toHashCode();
  }
}
