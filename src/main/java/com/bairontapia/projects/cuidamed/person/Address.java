package com.bairontapia.projects.cuidamed.person;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address", schema = "residence")
@Getter
@Setter
public class Address {

  @EmbeddedId
  @Setter(AccessLevel.PROTECTED)
  private AddressId id;

  @Column(name = "postal_code")
  private Integer postalCode;

  @Column(name = "fixed_phone")
  private Integer fixedPhone;

  @JoinColumn(name = "person_rut", referencedColumnName = "rut", insertable = false, updatable = false)
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Setter(AccessLevel.PROTECTED)
  private Person person;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Address address) {
      return Objects.equals(id, address.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
