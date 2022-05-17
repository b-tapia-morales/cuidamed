package com.bairontapia.projects.cuidamed.disease;


import com.bairontapia.projects.cuidamed.mappings.diseasetype.DiseaseType;

import java.util.Objects;


public record Disease(String name, DiseaseType diseaseType, Boolean isChronic) {

    public static Disease createInstance(String diseaseName, short diseaseType, boolean isChronic) {
        return new Disease(diseaseName, DiseaseType.getValueFromIndex(diseaseType), isChronic);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final Disease disease) {
            return Objects.equals(name, disease.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
