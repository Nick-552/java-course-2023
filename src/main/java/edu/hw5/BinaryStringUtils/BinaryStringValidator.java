package edu.hw5.BinaryStringUtils;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BinaryStringValidator {

    private static final Pattern VALID_BINARY_STRING_PATTERN = Pattern.compile("^[01]*$");

    public static void validate(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        if (!isBinary(string)) {
            throw new IllegalArgumentException("String should be binary");
        }
    }

    public static boolean isBinary(String string) {
        return VALID_BINARY_STRING_PATTERN.matcher(string).matches();
    }
}
