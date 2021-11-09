package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
@Getter
@Setter
public class SurgicalInterventionId implements Serializable {

  @Column(name = "elder_rut", nullable = false, updatable = false)
  private String rut;

  @Column(name = "intervention_date", nullable = false, updatable = false)
  private LocalDate interventionDate;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final SurgicalInterventionId id) {
      return new EqualsBuilder()
          .append(rut, id.rut)
          .append(interventionDate, id.interventionDate)
          .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(rut)
        .append(interventionDate)
        .toHashCode();
  }

}