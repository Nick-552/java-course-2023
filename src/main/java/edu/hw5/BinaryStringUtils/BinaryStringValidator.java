package edu.hw5.BinaryStringUtils;

import java.util.regex.Pattern;

public final class BinaryStringValidator {

    private static final Pattern VALID_BINARY_STRING_PATTERN = Pattern.compile("^[01]*$");

    private BinaryStringValidator() {}

    public static void validate(String string) {
        if (!isBinary(string)) {
            throw new IllegalArgumentException("String should be binary");
        }
    }

    public static boolean isBinary(String string) {
        return VALID_BINARY_STRING_PATTERN.matcher(string).matches();
    }
}
