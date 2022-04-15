package com.bairontapia.projects.cuidamed.mappings.allergytype;

import lombok.Getter;

@Getter
public enum AllergyType {
    DRUGS("Medicamentos"),
    FOODS("Comidas"),
    INSECTS("Insectos"),
    LATEX("Látex"),
    MOLD("Moho"),
    PETS("Mascotas"),
    POLLEN("Polen");

    private static final AllergyType[] VALUES = values();

    private final String name;

    AllergyType(String name) {
        this.name = name;
    }

    public static AllergyType[] getValues() {
        return VALUES;
    }

    public static AllergyType getValueFromIndex(final int index) {
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
