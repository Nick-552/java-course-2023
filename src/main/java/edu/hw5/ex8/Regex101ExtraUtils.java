package edu.hw5.ex8;

import java.util.regex.Pattern;

public class Regex101ExtraUtils {

    private static final Pattern PATTERN_1 = Pattern.compile("^.(.{2})*}$");

    private static final Pattern PATTERN_2 = Pattern.compile("^(0(.{2})*)|(1.(.{2})*)$");

    private static final Pattern PATTERN_3 = Pattern.compile("^([^0]*0[^0]*0[^0]*0[^0]*)*$");

    private static final Pattern PATTERN_4 = Pattern.compile("^(?!(11|111)$).*$");

    private static final Pattern PATTERN_5 = Pattern.compile("^$");

    private static final Pattern PATTERN_6 = Pattern.compile("^$");

    private static final Pattern PATTERN_7 = Pattern.compile("^$");

    private static final Pattern PATTERN_8 = Pattern.compile("^$");
}
