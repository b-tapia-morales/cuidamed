package com.bairontapia.projects.cuidamed.mappings.gender;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Hombre"),
    FEMALE("Mujer"),
    NOT_KNOWN("Desconocido"),
    NOT_APPLICABLE("No aplica");

    private static final Gender[] VALUES = values();

    private final String name;

    Gender(final String name) {
        this.name = name;
    }

    public static Gender getValueFromIndex(final int index) {
        if (index < 1 || index > VALUES.length) {
            throw new IllegalArgumentException();
        }
        return VALUES[index - 1];
    }

    public static Gender[] getValues() {
        return VALUES;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getIndex() {
        return ordinal() + 1;
    }
}
