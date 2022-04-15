package com.bairontapia.projects.cuidamed.mappings.diseasetype;

import lombok.Getter;

@Getter
public enum DiseaseType {
    ONCOLOGICAL("Oncológica"),
    INFECTIOUS("Infecciosa"),
    BLOOD("De la Sangre"),
    INMUNE("Del Sistema Inmune"),
    ENDOCRINE("Endocrina"),
    MENTAL("Mental"),
    NERVOUS_SYSTEM("Del Sistema Nervioso"),
    OPHTHALMOLOGIST("Oftalmológica"),
    AUDITORY("Auditiva"),
    CARDIOVASCULAR("Cardiovascular"),
    RESPIRATORY("Respiratoria"),
    DIGESTIVE("Digestiva"),
    SKIN("De la piel"),
    GENITOURINARY_SYSTEM("Sistema Genitourinario"),
    CONGENITAL("Congénitas y Alteraciones Cromosómicas");


    private static final DiseaseType[] VALUES = values();

    private final String name;

    DiseaseType(String name) {
        this.name = name;
    }

    public static DiseaseType[] getValues() {
        return VALUES;
    }

    public static DiseaseType getValueFromIndex(final int index) {
        if (index < 1 || index > VALUES.length) {
            throw new IllegalArgumentException();
        }
        return VALUES[index - 1];
    }

    @Override
    public String toString() {
        return name;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

}
