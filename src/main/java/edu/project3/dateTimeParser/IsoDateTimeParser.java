package edu.project3.dateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IsoDateTimeParser {

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.ISO_OFFSET_DATE_TIME
    );

    public static OffsetDateTime parse(String timeString) {
        OffsetDateTime dateTime;
        for (var formatter : DATE_TIME_FORMATTERS) {
            try {
                if (formatter.equals(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) {
                    dateTime = OffsetDateTime.of(
                        LocalDateTime.parse(timeString, formatter),
                        ZoneOffset.UTC
                    );
                } else if (formatter.equals(DateTimeFormatter.ISO_LOCAL_DATE)) {
                    dateTime = OffsetDateTime.of(
                        LocalDate.parse(timeString, formatter),
                        LocalTime.MIDNIGHT, ZoneOffset.UTC
                    );
                } else {
                    dateTime = OffsetDateTime.parse(timeString, formatter);
                }
            } catch (Exception e) {
                continue;
            }
            return dateTime;
        }
        return null;
    }
}
