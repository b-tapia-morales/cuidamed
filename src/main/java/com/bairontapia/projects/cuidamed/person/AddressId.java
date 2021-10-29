package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.localization.Commune;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class AddressId implements Serializable {
  @JoinColumn(name = "commune_id", updatable = false, nullable = false)
  @OneToOne(cascade = CascadeType.ALL)
  private Commune commune;

  @Column(name = "street", updatable = false, nullable = false)
  private String street;

  @Column(name = "number", updatable = false, nullable = false)
  private Short number;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_rut", updatable = false, nullable = false)
  private Person person;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final AddressId addressId) {
      return new EqualsBuilder()
              .append(commune, addressId.commune)
              .append(street, addressId.street)
              .append(number, addressId.number)
              .append(person, addressId.person)
              .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
            .append(commune)
            .append(street)
            .append(number)
            .append(person)
            .toHashCode();
  }
}
