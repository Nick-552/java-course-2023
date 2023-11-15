package edu.project3.datetimeparser;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class IsoDateTimeParser {

    private IsoDateTimeParser() {}

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = new ArrayList<>();

    static {
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ISO_LOCAL_DATE);
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ISO_OFFSET_DATE);
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ISO_DATE_TIME);
        DATE_TIME_FORMATTERS.add(DateTimeFormatter.ISO_DATE);
    }

    public static OffsetDateTime parse(String timeString) {
        for (var formatter : DATE_TIME_FORMATTERS) {
            try {
                return OffsetDateTime.parse(timeString, formatter);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        throw new RuntimeException("Can't parse date time");
    }
}
