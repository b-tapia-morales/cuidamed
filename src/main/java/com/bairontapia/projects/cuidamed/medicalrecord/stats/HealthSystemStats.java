package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;

public record HealthSystemStats(HealthCare healthCare, Integer frequency) {

    public static HealthSystemStats createInstance(short healthCare, Integer frequency) {
        return new HealthSystemStats(HealthCare.getValueFromIndex(healthCare), frequency);
    }

}
