package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import lombok.Getter;

public class DiseasePOJO {

    @Getter
    private final String name;
    @Getter
    private final String diseaseType;
    @Getter
    private final Boolean isChronic;

    public DiseasePOJO(final Disease disease) {
        name = disease.name();
        diseaseType = disease.diseaseType().toString();
        isChronic = disease.isChronic();
    }

}
