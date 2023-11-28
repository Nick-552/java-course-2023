package edu.project3.dateTimeParser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class IsoDateTimeParserTest {

    public static Stream<Arguments> sourceForParsing() {
        return Stream.of(
            Arguments.of("2023-11-19", OffsetDateTime.of(2023, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC)),
            Arguments.of("2023-11-19T10:38:55", OffsetDateTime.of(2023, 11, 19, 10, 38, 55, 0, ZoneOffset.UTC)),
            Arguments.of("2023-11-19T10:38:55+00:00", OffsetDateTime.of(2023, 11, 19, 10, 38, 55, 0, ZoneOffset.UTC)),
            Arguments.of("2023/11/19", null)
        );
    }

    @ParameterizedTest
    @MethodSource("sourceForParsing")
    @DisplayName("parse")
    void parse_shouldReturnParsedOffsetDateTimeIfIsoLocalOrOffsetDateOrDateTimeElseNull(
        String dateTimeToParse, OffsetDateTime offsetDateTimeExpected) {
        assertThat(IsoDateTimeParser.parse(dateTimeToParse)).isEqualTo(offsetDateTimeExpected);
    }
}
