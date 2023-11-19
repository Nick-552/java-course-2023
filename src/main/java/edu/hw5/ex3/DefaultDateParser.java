package edu.hw5.ex3;

import edu.hw5.ex3.customParsers.DateParserDaysAgo;
import edu.hw5.ex3.customParsers.DateParserTodayYesterdayTomorrow;
import edu.hw5.ex3.customParsers.DateParserWithDashSeparator;
import edu.hw5.ex3.customParsers.DateParserWithSlashSeparator;
import edu.hw5.ex3.customParsers.DateParserWithSlashSeparatorTwoYearDigits;
import edu.hw5.ex3.model.DateParserChain;
import java.time.LocalDate;
import java.util.Optional;

public final class DefaultDateParser {

    private static final DateParserChain DATE_PARSER_CHAIN = new DateParserChain(
        new DateParserWithDashSeparator(),
        new DateParserWithSlashSeparator(),
        new DateParserWithSlashSeparatorTwoYearDigits(),
        new DateParserTodayYesterdayTomorrow(),
        new DateParserDaysAgo()
    );

    private DefaultDateParser() {}

    public static Optional<LocalDate> parse(String dateString) {
        return DATE_PARSER_CHAIN.parseDate(dateString);
    }
}
