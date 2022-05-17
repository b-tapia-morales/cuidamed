package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.medicalrecord.allergy.Allergy;
import lombok.Getter;

public class AllergyPOJO {
    @Getter
    private final String allergyType;
    @Getter
    private final String details;

    public AllergyPOJO(final Allergy allergy) {
        allergyType = allergy.type().toString();
        details = allergy.details();
    }
}
