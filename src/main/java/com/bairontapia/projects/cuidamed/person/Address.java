package com.bairontapia.projects.cuidamed.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Address(String regionName, String provinceName, String communeName, String street,
                      Integer number, Integer postalCode, Integer fixedPhone, String rut) {

  @Override
  public String toString() {
    return String.format
        ("""
                        
            Región:\t\t\t\t\t\t\t\t%s
            Provincia:\t\t\t\t\t\t%s
            Comuna:\t\t\t\t\t\t\t\t%s
            Calle:\t\t\t\t\t\t\t\t%s
            Número:\t\t\t\t\t\t\t\t%s
            """, regionName, provinceName, communeName, street, number);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Address address) {
      return new EqualsBuilder()
          .append(communeName, address.communeName)
          .append(street, address.street)
          .append(number, address.number)
          .append(rut, address.rut)
          .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(communeName)
        .append(street)
        .append(number)
        .append(rut)
        .toHashCode();
  }
}
