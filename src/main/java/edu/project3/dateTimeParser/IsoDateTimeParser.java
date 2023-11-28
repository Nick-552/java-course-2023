package edu.project3.dateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IsoDateTimeParser {

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.ISO_OFFSET_DATE_TIME
    );

    public static OffsetDateTime parse(String timeString) {
        for (var formatter : DATE_TIME_FORMATTERS) {
            try {
                if (formatter.equals(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) {
                    return OffsetDateTime.of(
                        LocalDateTime.parse(timeString, formatter),
                        ZoneOffset.UTC
                    );
                } else if (formatter.equals(DateTimeFormatter.ISO_LOCAL_DATE)) {
                    return OffsetDateTime.of(
                        LocalDate.parse(timeString, formatter),
                        LocalTime.MIDNIGHT, ZoneOffset.UTC
                    );
                } else {
                    return OffsetDateTime.parse(timeString, formatter);
                }
            } catch (Exception ignored) { }
        }
        return null;
    }
}
