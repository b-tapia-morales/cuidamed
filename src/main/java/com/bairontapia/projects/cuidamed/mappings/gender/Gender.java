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
    private final String name;

    private static final Gender[] VALUES = values();
    private static final int[] CODES = Stream.of(VALUES).mapToInt(Gender::getCode).toArray();
    private static final String[] NAMES = Stream.of(VALUES).map(Gender::getName).toArray(String[]::new);
    private static final Map<Integer, Gender> VALUES_MAP = IntStream
            .range(0, VALUES.length)
            .boxed()
            .collect(Collectors.toMap(i -> CODES[i], i -> VALUES[i], (a, b) -> a, LinkedHashMap::new));
    private static final Map<String, Gender> NAMES_MAP = IntStream
            .range(0, VALUES.length)
            .boxed()
            .collect(Collectors.toMap(i -> NAMES[i], i -> VALUES[i], (a, b) -> a, LinkedHashMap::new));

    Gender(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static Gender getValueFromCode(final int code) {
        if (!VALUES_MAP.containsKey(code)) {
            throw new IllegalArgumentException();
        }
        return VALUES_MAP.get(code);
    }

    public static Gender getValueFromName(final String name) {
        if (!NAMES_MAP.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return NAMES_MAP.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
