package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class AllergyId implements Serializable {
  @Column(name = "elder_rut", nullable = false, updatable = false)
  private String rut;

  @Column(name = "allergy_name", nullable = false, updatable = false)
  private String allergyName;

  @Override
  public boolean equals(final Object object) {
    if (this == object) return true;

    if (object instanceof final AllergyId id) {
      return new EqualsBuilder()
              .append(rut, id.rut)
              .append(allergyName, id.allergyName)
              .isEquals();
    };
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
            .append(rut)
            .append(allergyName)
            .toHashCode();
  }
}
