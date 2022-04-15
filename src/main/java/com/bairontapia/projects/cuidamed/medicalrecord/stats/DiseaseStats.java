package com.bairontapia.projects.cuidamed.medicalrecord.stats;

public record DiseaseStats(String diseaseName, Integer frequency) {

    public static DiseaseStats createInstance(String diseaseName, Integer frequency) {
        return new DiseaseStats(diseaseName, frequency);
    }
}
