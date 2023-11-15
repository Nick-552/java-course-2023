package edu.hw5.ex3.customParsers;

import edu.hw5.ex3.model.DateParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.Optional;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

public class DateParserWithDashSeparator extends DateParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
        .appendValue(YEAR, DEFAULT_YEAR_NUMBER_OF_DIGITS)
        .appendLiteral("-")
        .appendValue(MONTH_OF_YEAR, 1, 2, SignStyle.NOT_NEGATIVE)
        .appendLiteral("-")
        .appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
        .toFormatter();

    @Override
    protected Optional<LocalDate> selfParse(String dateString) {
        try {
            return Optional.of(LocalDate.parse(dateString, DATE_TIME_FORMATTER));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
