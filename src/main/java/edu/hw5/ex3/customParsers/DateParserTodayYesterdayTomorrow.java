package edu.hw5.ex3.customParsers;

import edu.hw5.ex3.model.DateParser;
import java.time.LocalDate;
import java.util.Optional;

public class DateParserTodayYesterdayTomorrow extends DateParser {

    @Override
    protected Optional<LocalDate> selfParse(String dateString) {
        return switch (dateString) {
            case "today" -> Optional.of(LocalDate.now());
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            default -> Optional.empty();
        };
    }
}
