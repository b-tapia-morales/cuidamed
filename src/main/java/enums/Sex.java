package enums;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Sex {

    NOT_KNOWN(0, "Desconocido"),
    MALE(1, "Hombre"),
    FEMALE(2, "Mujer"),
    NOT_APPLICABLE(9, "No aplica");

    private final int code;
    private final String value;

    private static final Sex[] VALUES = values();
    private static final int[] CODES = Stream.of(VALUES).mapToInt(Sex::getCode).toArray();
    private static final String[] REPRESENTATIONS = Stream.of(VALUES).map(Sex::getValue).toArray(String[]::new);
    private static final Map<Integer, String> MAP = IntStream
            .range(0, VALUES.length)
            .boxed()
            .collect(Collectors.toMap(i -> CODES[i], i -> REPRESENTATIONS[i], (a, b) -> a, LinkedHashMap::new));

    Sex(final int code, final String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueFromCode(final int code) {
        if (!MAP.containsKey(code)) {
            throw new IllegalArgumentException();
        }
        return MAP.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
