package edu.hw3.ex4;

import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("checkstyle:MagicNumber")
public final class DecimalToRomanConverter {
    private static final Map<Integer, String> ROMAN_VALUES = new TreeMap<>();

    static {
        ROMAN_VALUES.put(1000, "M");
        ROMAN_VALUES.put(900, "CM");
        ROMAN_VALUES.put(500, "D");
        ROMAN_VALUES.put(400, "CD");
        ROMAN_VALUES.put(100, "C");
        ROMAN_VALUES.put(90, "XC");
        ROMAN_VALUES.put(50, "L");
        ROMAN_VALUES.put(40, "XL");
        ROMAN_VALUES.put(10, "X");
        ROMAN_VALUES.put(9, "IX");
        ROMAN_VALUES.put(5, "V");
        ROMAN_VALUES.put(4, "IV");
        ROMAN_VALUES.put(1, "I");
    }

    private DecimalToRomanConverter() {}

    public static String convert(int number) {
        if (!(number > 0 && number < 4000)) {
            throw new IllegalArgumentException("The number must belong to the interval [1, 3999]");
        }
        int tmp = number;
        StringBuilder romanNumber = new StringBuilder();
        for (var entry : ROMAN_VALUES.entrySet()) {
            while (tmp > entry.getKey()) {
                romanNumber.append(entry.getValue());
                tmp -= entry.getKey();
            }
        }
        return romanNumber.toString();
    }
}
