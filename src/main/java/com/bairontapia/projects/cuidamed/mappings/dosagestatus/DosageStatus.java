package com.bairontapia.projects.cuidamed.mappings.dosagestatus;

import lombok.Getter;

@Getter
public enum DosageStatus {
    UNDEFINED("Sin definir"),
    PENDING("Pendiente"),
    UNFULFILLED("Incumplido"),
    FULFILLED("Cumplido");

    private static final DosageStatus[] VALUES = values();

    private final String status;

    DosageStatus(final String status) {
        this.status = status;
    }

    public static DosageStatus[] getValues() {
        return VALUES;
    }

    public static DosageStatus getValueFromIndex(final int index) {
        if (index < 1 || index > VALUES.length) {
            throw new IllegalArgumentException();
        }
        return VALUES[index - 1];
    }

    @Override
    public String toString() {
        return status;
    }

    public int getIndex() {
        return ordinal() + 1;
    }
}
