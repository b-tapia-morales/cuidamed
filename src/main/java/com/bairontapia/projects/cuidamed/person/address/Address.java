package com.bairontapia.projects.cuidamed.person.address;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Address(Short communeId, String street, Short number, Integer postalCode,
                      Integer fixedPhone, String rut) {

    public static Address createInstance(Short communeId, String street, Short number,
                                         Integer postalCode, Integer fixedPhone, String rut) {
        return new Address(communeId, street, number, postalCode, fixedPhone, rut);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final Address address) {
            return new EqualsBuilder()
                    .append(communeId, address.communeId)
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
                .append(communeId)
                .append(street)
                .append(number)
                .append(rut)
                .toHashCode();
    }
}
