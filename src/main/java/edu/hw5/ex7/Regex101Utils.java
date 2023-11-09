package edu.hw5.ex7;

import java.util.regex.Pattern;
import static edu.hw5.BinaryStringUtils.BinaryStringValidator.validate;

public class Regex101Utils {

    private static final Pattern PATTERN_1 = Pattern.compile("^.{2}0.*$");

    private static final Pattern PATTERN_2 = Pattern.compile("^(.).*\\1$");

    private static final Pattern PATTERN_3 = Pattern.compile("^.{1,3}$");

    public boolean hasAtLeast3CharsAnd3rdIs0(String string) {
        validate(string);
        return PATTERN_1.matcher(string).matches();
    }

    public boolean hasSameStartAndEndChars(String string) {
        validate(string);
        return PATTERN_2.matcher(string).matches();
    }

    public boolean hasLengthInRange1to3(String string) {
        validate(string);
        return PATTERN_3.matcher(string).matches();
    }
}
