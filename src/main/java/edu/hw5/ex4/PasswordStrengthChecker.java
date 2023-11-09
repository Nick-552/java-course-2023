package edu.hw5.ex4;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class PasswordStrengthChecker {

    private static final Pattern REQUIRED_CHARACTERS = Pattern.compile("[~!@#$%^&*|]");

    private PasswordStrengthChecker() {}

    public static boolean isStrong(@NotNull String password) {
        return REQUIRED_CHARACTERS.matcher(password).find();
    }
}
