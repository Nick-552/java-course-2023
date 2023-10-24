package edu.hw3.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class WordFrequencyTest {

    private static Stream<Arguments> wordSource() {
        return Stream.of(
            Arguments.of(
                List.of("a", "bb", "a", "bb"),
                Map.of("bb", 2, "a", 2)
            ),
            Arguments.of(
                List.of("this", "and", "that", "and"),
                Map.of("that", 1, "and", 2, "this", 1)
            ),
            Arguments.of(
                List.of("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)
            ),
            Arguments.of(
                List.of(1, 1, 2, 2),
                Map.of(1, 2, 2, 2)
            ),
            Arguments.of(
                List.of(),
                Map.of()
            ),
            Arguments.of(
                null,
                Map.of()
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Basic test")
    @MethodSource("wordSource")
    <T> void count_shouldReturnFrequencyMap_whenValidInput(List<T> words, Map<T, Integer> expectedMap) {
        assertThat(WordFrequency.count(words)).isEqualTo(expectedMap);
    }
}
