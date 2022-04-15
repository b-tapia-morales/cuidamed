package com.bairontapia.projects.cuidamed.mappings.pharmaceuticalform;

import lombok.Getter;

@Getter
public enum PharmaceuticalForm {
    CAPSULES("Cápsulas"),
    TABLETS("Comprimidos"),
    CHEWING_GUMS("Gomas de mascar"),
    SUSPENSIONS("Suspensiones"),
    SYRINGES("Jeringas");

    private static final PharmaceuticalForm[] VALUES = values();

    private final String form;

    PharmaceuticalForm(final String form) {
        this.form = form;
    }

    public static PharmaceuticalForm[] getValues() {
        return VALUES;
    }

    public static PharmaceuticalForm getValueFromIndex(final int index) {
        if (index < 1 || index > VALUES.length) {
            throw new IllegalArgumentException();
        }
        return VALUES[index - 1];
    }

    @Override
    public String toString() {
        return form;
    }

    public int getIndex() {
        return ordinal() + 1;
    }
}
