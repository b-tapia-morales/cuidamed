package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.localization.Commune;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class AddressId implements Serializable {

  @Column(name = "commune_id", updatable = false, nullable = false)
  private Short communeId;

  @Column(name = "street", updatable = false, nullable = false)
  private String street;

  @Column(name = "number", updatable = false, nullable = false)
  private Short number;

  @Column(name = "person_rut", updatable = false, nullable = false)
  private String rut;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final AddressId addressId) {
      return new EqualsBuilder()
          .append(communeId, addressId.communeId)
          .append(street, addressId.street)
          .append(number, addressId.number)
          .append(rut, addressId.rut)
          .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(communeId)
        .append(street)
        .append(number)
        .append(rut)
        .toHashCode();
  }
}
