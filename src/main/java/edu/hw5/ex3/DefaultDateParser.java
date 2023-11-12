package edu.hw5.ex3;

import edu.hw5.ex3.customParsers.DateParser1;
import edu.hw5.ex3.customParsers.DateParser2;
import edu.hw5.ex3.customParsers.DateParser3;
import edu.hw5.ex3.customParsers.DateParser4;
import edu.hw5.ex3.customParsers.DateParser5;
import edu.hw5.ex3.model.DateParserChain;
import java.time.LocalDate;
import java.util.Optional;

public final class DefaultDateParser {

    private static final DateParserChain DATE_PARSER_CHAIN = new DateParserChain(
        new DateParser1(),
        new DateParser2(),
        new DateParser3(),
        new DateParser4(),
        new DateParser5()
    );

    private DefaultDateParser() {}

    public static Optional<LocalDate> parse(String dateString) {
        return DATE_PARSER_CHAIN.parseDate(dateString);
    }
}
