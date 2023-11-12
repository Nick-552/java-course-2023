package edu.hw5.ex3.customParsers;

import edu.hw5.ex3.model.DateParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class DateParser3 extends DateParser {

    private static final int CURRENT_MILLENNIUM = 2000;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
        .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
        .appendLiteral("/")
        .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NOT_NEGATIVE)
        .appendLiteral("/")
        .appendValue(ChronoField.YEAR, 2)
        .toFormatter();

    @Override
    protected Optional<LocalDate> selfParse(@NotNull String dateString) {
        try {
            return Optional.of(LocalDate.parse(dateString, DATE_TIME_FORMATTER).plusYears(CURRENT_MILLENNIUM));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
