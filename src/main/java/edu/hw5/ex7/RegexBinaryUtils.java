package edu.hw5.ex7;

import java.util.regex.Pattern;
import static edu.hw5.BinaryStringUtils.BinaryStringValidator.validate;

public final class RegexBinaryUtils {

    private static final Pattern PATTERN_1 = Pattern.compile("^.{2}0.*$");

    private static final Pattern PATTERN_2 = Pattern.compile("^(.).*\\1|1|0$");

    private static final Pattern PATTERN_3 = Pattern.compile("^.{1,3}$");

    private RegexBinaryUtils() {}

    public static boolean hasAtLeastThreeCharsAnd3rdIs0(String string) {
        validate(string);
        return PATTERN_1.matcher(string).matches();
    }

    public static boolean hasSameStartAndEndChars(String string) {
        validate(string);
        return PATTERN_2.matcher(string).matches();
    }

    public static boolean hasLengthInRangeOneToThree(String string) {
        validate(string);
        return PATTERN_3.matcher(string).matches();
    }
}
