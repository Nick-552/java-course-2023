package edu.hw5.ex8;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import static edu.hw5.BinaryStringUtils.BinaryStringValidator.validate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexExtraBinaryUtils {

    private static final Pattern PATTERN_1 = Pattern.compile("^.(.{2})*$");

    private static final Pattern PATTERN_2 = Pattern.compile("^((0(.{2})*)|(1.(.{2})*))$");

    private static final Pattern PATTERN_3 = Pattern.compile("^([^0]*0[^0]*0[^0]*0[^0]*)*$");

    private static final Pattern PATTERN_4 = Pattern.compile("^(?!(11|111)$).*$");

    private static final Pattern PATTERN_5 = Pattern.compile("^(1.)*(1)?$");

    private static final Pattern PATTERN_6 = Pattern.compile("(?=(^.*0.*0.*$))(?=(^[^1]*(1)?[^1]*$)).*");

    private static final Pattern PATTERN_7 = Pattern.compile("^0*(10+)*(1)?0*$");

    public static boolean hasOddLength(String binaryString) {
        validate(binaryString);
        return PATTERN_1.matcher(binaryString).matches();
    }

    public static boolean startsWith0AndOddLengthOrStartsWith1AndEvenLength(String binaryString) {
        validate(binaryString);
        return PATTERN_2.matcher(binaryString).matches();
    }

    public static boolean hasNumberOf0sThatIsMultipleOfThree(String binaryString) {
        validate(binaryString);
        return PATTERN_3.matcher(binaryString).matches();
    }

    public static boolean isAnyStringExcept11And111(String binaryString) {
        validate(binaryString);
        return PATTERN_4.matcher(binaryString).matches();
    }

    public static boolean isEachOddChar1(String binaryString) {
        validate(binaryString);
        return PATTERN_5.matcher(binaryString).matches();
    }

    public static boolean containsAtLeastTwo0sAndNoMoreThanOne1(String binaryString) {
        validate(binaryString);
        return PATTERN_6.matcher(binaryString).matches();
    }

    public static boolean noConsecutive1s(String binaryString) {
        validate(binaryString);
        return PATTERN_7.matcher(binaryString).matches();
    }
}
