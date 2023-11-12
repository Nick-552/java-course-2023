package edu.hw5.ex4;

import java.util.regex.Pattern;

public final class PasswordStrengthChecker {

    private static final Pattern REQUIRED_CHARACTERS = Pattern.compile("[~!@#$%^&*|]");

    private PasswordStrengthChecker() {}

    public static boolean isStrong(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        return REQUIRED_CHARACTERS.matcher(password).find();
    }
}
