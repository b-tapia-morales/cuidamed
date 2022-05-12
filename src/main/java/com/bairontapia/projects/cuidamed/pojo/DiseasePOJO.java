package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.mappings.diseasetype.DiseaseType;
import lombok.Getter;

public class DiseasePOJO {
    @Getter
    private final String diseaseName;
    @Getter
    private final DiseaseType diseaseType;
    @Getter
    private final Boolean isChronic;

    public DiseasePOJO(final Disease disease){
        diseaseName = disease.disease_name();
        diseaseType = disease.diseaseType();
        isChronic = disease.is_chronic();
    }


}
