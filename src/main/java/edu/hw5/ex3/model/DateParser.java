package edu.hw5.ex3.model;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {

    protected static final int DEFAULT_YEAR_NUMBER_OF_DIGITS = 4;

    private DateParser next = null;

    public void linkNextDateParser(DateParser next) {
        this.next = next;
    }

    public Optional<LocalDate> parse(String dateString) {
        if (dateString == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        Optional<LocalDate> date = selfParse(dateString);
        if (date.isPresent()) {
            return date;
        } else if (next != null) {
            return next.parse(dateString);
        }
        return Optional.empty();
    }

    protected abstract Optional<LocalDate> selfParse(String dateString);
}
