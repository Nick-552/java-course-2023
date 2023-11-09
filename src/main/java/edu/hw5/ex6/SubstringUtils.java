package edu.hw5.ex6;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class SubstringUtils {

    private SubstringUtils() {}

    static boolean isSubstringOf(@NotNull String targetString, @NotNull String sourceString) {
        Pattern substringPattern = Pattern.compile(targetString);
        return substringPattern.matcher(sourceString).find();
    }
}
