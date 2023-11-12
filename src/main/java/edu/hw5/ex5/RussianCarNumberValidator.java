package edu.hw5.ex5;

import java.util.regex.Pattern;

public final class RussianCarNumberValidator {

    private static final Pattern RUSSIAN_CAR_NUMBER_PATTERN = Pattern
        .compile("^[УКЕНХВАРОСМТ]\\d{3}[УКЕНХВАРОСМТ]{2}\\d{2,3}");

    private RussianCarNumberValidator() {}

    public static boolean isValid(String russianCarNumber) {
        if (russianCarNumber == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        return RUSSIAN_CAR_NUMBER_PATTERN.matcher(russianCarNumber).matches();
    }
}
