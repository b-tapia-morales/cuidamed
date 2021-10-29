package com.bairontapia.projects.cuidamed.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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
  private String fixed_phone;

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
