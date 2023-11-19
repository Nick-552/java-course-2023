package edu.hw5.ex6;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SubstringUtils {

    static boolean isSubstringOf(String targetString, String sourceString) {
        if (sourceString == null || targetString == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        Pattern substringPattern = Pattern.compile(targetString);
        return substringPattern.matcher(sourceString).find();
    }
}
