package edu.hw5.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class GameSessionTimeTrackerTest {

    private static Stream<Arguments> basicSource() {
        return Stream.of(
            Arguments.of(
                List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"),
                "3ч 40м"
            ),
            Arguments.of(
                List.of("2023-03-31, 17:45 - 2023-03-31, 23:45", "2023-04-01, 16:00 - 2023-04-01, 20:00"),
                "5ч 0м"
            )
        );
    }

    private static Stream<Arguments> invalidSource() {
        return Stream.of(
            Arguments.of(
                List.of("2022-04-02, 21:30 - 2022-04-01, 01:20", "2022-03-12, 23:20 - 2022-03-12, 20:50")
            ),
            Arguments.of(List.of("2022-04-02, 21:30 - 2022-04-02, 21:30")),
            Arguments.of(List.of("asdgafgsdf", "dfgdfhd"))
        );
    }

    @ParameterizedTest
    @MethodSource("basicSource")
    @DisplayName("Basic test")
    void getAverageGameSessionDuration_shouldReturnAverageDuration_whenValidInput(List<String> sessions, String expected) {
        assertThat(GameSessionTimeTracker.getAverageGameSessionTime(sessions)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null and empty source")
    void getAverageGameSessionDuration_shouldThrowIllegalArgumentException_whenNullOrEmpty(List<String> sessions) {
        assertThatIllegalArgumentException()
            .isThrownBy(
                () -> GameSessionTimeTracker.getAverageGameSessionTime(sessions)
            );
    }

    @ParameterizedTest
    @MethodSource("invalidSource")
    @DisplayName("Invalid source")
    void getAverageGameSessionDuration_shouldThrowIllegalArgumentException_whenStartAfterEnd(List<String> sessions) {
        assertThatIllegalArgumentException()
            .isThrownBy(
                () -> GameSessionTimeTracker.getAverageGameSessionTime(sessions)
            );
    }
}
