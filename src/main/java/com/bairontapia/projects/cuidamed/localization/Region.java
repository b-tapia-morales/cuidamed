package com.bairontapia.projects.cuidamed.localization;

import java.util.Objects;

public record Region(Short id, String name, String abbreviation, String capital) {

    public static Region createInstance(short id, String name, String abbreviation, String capital) {
        return new Region(id, name, abbreviation, capital);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof final Region region) {
            return Objects.equals(id, region.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
