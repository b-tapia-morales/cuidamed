package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class RoutineCheckupId implements Serializable {
  @Column(name = "elder_rut", nullable = false, updatable = false)
  private String rut;

  @Column(name = "checkup_date", nullable = false, updatable = false)
  private LocalDate checkupDate;

  @Override
  public boolean equals(final Object object) {
    if (this == object) return true;

    if (object instanceof final RoutineCheckupId id) {
      return new EqualsBuilder()
              .append(rut, id.rut)
              .append(checkupDate, id.checkupDate)
              .isEquals();
    };
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
            .append(rut)
            .append(checkupDate)
            .toHashCode();
  }
}
