package edu.hw5.ex3.model;

import java.time.LocalDate;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

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

    public Optional<LocalDate> parseDate(@NotNull String dateString) {
        return head.parse(dateString);
    }
}
