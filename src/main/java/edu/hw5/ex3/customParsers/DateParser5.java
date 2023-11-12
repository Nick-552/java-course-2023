package edu.hw5.ex3.customParsers;

import edu.hw5.ex3.model.DateParser;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser5 extends DateParser {

    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d+)\\s+day(s?)\\s+ago");

    @Override
    protected Optional<LocalDate> selfParse(String dateString) {
        Matcher matcher = DATE_PATTERN.matcher(dateString);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(1))));
    }
}
