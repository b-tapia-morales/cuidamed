package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record Allergy(String rut, AllergyType type, String details) {

    public static Allergy createInstance(String rut, short allergyTypeCode, String details) {
        return new Allergy(rut, AllergyType.getValueFromIndex(allergyTypeCode), details);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final Allergy allergy) {
            return new EqualsBuilder()
                    .append(rut, allergy.rut)
                    .append(details, allergy.details)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(rut)
                .append(details)
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format
                ("""
                                Rut:\t\t\t\t\t\t\t\t\t%s
                                Tipo de alergia:\t\t\t%s
                                Nombre de la alergia:\t%s
                                """, RutUtils.format(rut),
                        type, details);
    }
}

