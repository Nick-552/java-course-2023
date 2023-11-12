package edu.hw5.ex2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class Friday13thFinderTest {

    private static Stream<Arguments> allFridays13thInYearSource() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            )
        );
    }


    private static Stream<Arguments> nextFriday13thSource() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2023, 11, 12),
                LocalDate.of(2024, 9, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 9, 13),
                LocalDate.of(2024, 12, 13)
            )
        );
    }



    @ParameterizedTest
    @MethodSource("allFridays13thInYearSource")
    @DisplayName("allFridays13thInYear")
    void allFridays13inYear_shouldReturnListOfFriday13th_whenAnyYearGiven(int year, List<LocalDate> expected) {
        assertThat(Friday13thFinder.allFridays13inYear(year)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("nextFriday13thSource")
    @DisplayName("nextFriday13th")
    void nextFriday13_shouldReturnNextFriday13th_whenAnyDateProvided(LocalDate date, LocalDate expected) {
        assertThat(Friday13thFinder.nextFriday13(date)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null source date")
    void nextFriday13_shouldReturnNextFriday13th_whenNullProvided(LocalDate date) {
        assertThatIllegalArgumentException().isThrownBy(() -> Friday13thFinder.nextFriday13(date));
    }
}
