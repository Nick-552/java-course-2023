package edu.project3.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class FilterTest {

    public static Stream<Arguments> sourceForFilteringList() {
        return Stream.of(
            Arguments.of(
                new Filter<Integer>() {
                    @Override
                    protected boolean passFiltration(Integer element) {
                        return element > 0;
                    }
                },
                List.of(4,-1, 0, 7, -11, 12, -1, 999),
                List.of(4, 7, 12, 999)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("sourceForFilteringList")
    @DisplayName("filtering list")
    void filter_shouldReturnFilteredList(Filter<Integer> filter, List<Integer> list, List<Integer> filteredList) {
        assertThat(filter.filter(list)).isEqualTo(filteredList);
    }
}
