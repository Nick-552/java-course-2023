package edu.hw5.ex3.model;

import java.time.LocalDate;
import java.util.Optional;

public class DateParserChain {
    private final DateParser head;

    public DateParserChain(DateParser head, DateParser... dateParsers) {
        this.head = head;
        var tmp = head;
        for (var dateParser : dateParsers) {
            tmp.linkNextDateParser(dateParser);
            tmp = dateParser;
        }
    }

    public Optional<LocalDate> parseDate(String dateString) {
        if (dateString == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        return head.parse(dateString);
    }
}
