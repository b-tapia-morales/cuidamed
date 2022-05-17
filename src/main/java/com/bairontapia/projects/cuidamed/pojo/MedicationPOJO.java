package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medication.Medication;
import lombok.Getter;

public class MedicationPOJO {

    @Getter
    private final String name;
    @Getter
    private final String administrationRoute;
    @Getter
    private final String measureUnit;
    @Getter
    private final String pharmaceuticalForm;

    public MedicationPOJO(final Medication medication) {
        name = medication.name();
        administrationRoute = medication.administrationRoute().toString();
        measureUnit = medication.measureUnit().toString();
        pharmaceuticalForm = medication.pharmaceuticalForm().toString();
    }

}
