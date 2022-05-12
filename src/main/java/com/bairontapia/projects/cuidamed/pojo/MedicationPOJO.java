package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.medication.Medication;
import com.bairontapia.projects.cuidamed.mappings.administrationroute.AdministrationRoute;
import com.bairontapia.projects.cuidamed.mappings.measureunit.MeasureUnit;
import com.bairontapia.projects.cuidamed.mappings.pharmaceuticalform.PharmaceuticalForm;
import lombok.Getter;

public class MedicationPOJO {

    @Getter
    private final String medicationName;

    @Getter
    private final AdministrationRoute admiRoute;

    @Getter
    private final MeasureUnit measureUnit;

    @Getter
    private final PharmaceuticalForm pharmaceuticalForm;


    public MedicationPOJO(final Medication medication){
        medicationName = medication.nameMedication();
        admiRoute = medication.administrationRoute();
        measureUnit = medication.measureUnit();
        pharmaceuticalForm = medication.pharmaceuticalForm();


    }

}
