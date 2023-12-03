package edu.hw8.ex2;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class FibonacciUtilsTest {

    public static Stream<Arguments> fibonacciSource() {
        return Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(22, 17711),
            Arguments.of(5, 5),
            Arguments.of(10, 55)
        );
    }

    @ParameterizedTest
    @MethodSource("fibonacciSource")
    @DisplayName("Compute Fibonacci number")
    void compute(int num, long fibonacciNum) {
        assertThat(FibonacciUtils.compute(num)).isEqualTo(fibonacciNum);
    }
}
