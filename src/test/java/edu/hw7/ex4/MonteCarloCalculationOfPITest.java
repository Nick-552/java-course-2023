package edu.hw7.ex4;

import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class MonteCarloCalculationOfPITest {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String LOG_STRING = "Время %sпоточного выполнения %s итераций: %s sec %s ms; pi = %s";
    private static final double EPSILON = 0.005;

    public static Stream<Arguments> iterationsSource() {
        return Stream.of(
            Arguments.of(1_000_000),
            Arguments.of(10_000_000),
            Arguments.of(100_000_000),
            Arguments.of(1_000_000_000)
        );
    }

    @ParameterizedTest
    @MethodSource("iterationsSource")
    @DisplayName("not parallel accuracy test")
    void calculatePiNotParallel(int iterations) {
        long startTime = System.nanoTime();
        double pi = MonteCarloCalculationOfPI
            .calculatePiNotParallel(iterations);
        long endTime = System.nanoTime();
        assertThat(pi).isCloseTo(Math.PI, Offset.offset(EPSILON));
        long time = endTime - startTime;
        LOGGER.info(String.format(LOG_STRING, "одно", iterations, time / 1_000_000_000, time % 1_000_000_000 / 1_000_000, pi));
    }

    @ParameterizedTest
    @MethodSource("iterationsSource")
    @DisplayName("parallel accuracy test")
    void calculatePiParallel(int iterations) {
        long startTime = System.nanoTime();
        double pi = MonteCarloCalculationOfPI
            .calculatePiParallel(iterations);
        long endTime = System.nanoTime();
        assertThat(pi).isCloseTo(Math.PI, Offset.offset(EPSILON));
        long time = endTime - startTime;
        LOGGER.info(String.format(LOG_STRING, "много", iterations, time / 1_000_000_000, time % 1_000_000_000 / 1_000_000, pi));
    }
}
