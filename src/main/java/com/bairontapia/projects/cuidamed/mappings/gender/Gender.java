package com.bairontapia.projects.cuidamed.mappings.gender;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Gender {

    NOT_KNOWN(0, "Desconocido"),
    MALE(1, "Hombre"),
    FEMALE(2, "Mujer"),
    NOT_APPLICABLE(9, "No aplica");

    private final int code;
    private final String string;

    private static final Gender[] ARRAY = values();
    private static final int[] CODES = Stream.of(ARRAY).mapToInt(Gender::getCode).toArray();
    private static final Map<Integer, Gender> MAP = IntStream
            .range(0, ARRAY.length)
            .boxed()
            .collect(Collectors.toMap(i -> CODES[i], i -> ARRAY[i], (a, b) -> a, LinkedHashMap::new));

    Gender(final int code, final String string) {
        this.code = code;
        this.string = string;
    }

    public static Gender getValueFromCode(final int code) {
        if (!MAP.containsKey(code)) {
            throw new IllegalArgumentException();
        }
        return MAP.get(code);
    }

    @Override
    public String toString() {
        return string;
    }

    public int getCode() {
        return code;
    }

    public String getString() {
        return string;
    }

}
