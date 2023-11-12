package edu.hw5.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DefaultDateParserTest {

    private static Stream<Arguments> dateSource() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                Optional.of(LocalDate.of(2020, 10, 10))
            ),
            Arguments.of(
                "2020-12-2",
                Optional.of(LocalDate.of(2020, 12, 2))
            ),
            Arguments.of(
                "1/3/1976",
                Optional.of(LocalDate.of(1976, 3, 1))
            ),
            Arguments.of(
                "1/3/20",
                Optional.of(LocalDate.of(2020, 3, 1))
            ),
            Arguments.of(
                "tomorrow",
                Optional.of(LocalDate.now().plusDays(1))
            ),
            Arguments.of(
                "today",
                Optional.of(LocalDate.now())
            ),
            Arguments.of(
                "yesterday",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "1 day ago",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "2234 days ago",
                Optional.of(LocalDate.now().minusDays(2234))
            ),
            Arguments.of(
                "20-10-10",
                Optional.empty()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("dateSource")
    @DisplayName("Parse test")
    void parse_shouldReturnLocalDateWhenRightFormatAndEmptyWhenWrong(String dateToParse, Optional<LocalDate> expected) {
        assertThat(DefaultDateParser.parse(dateToParse)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test")
    void parse_shouldThrowIllegalArgumentException_whenNull(String dateToParse) {
        assertThatIllegalArgumentException().isThrownBy(() -> DefaultDateParser.parse(dateToParse));
    }
}
