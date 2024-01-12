package edu.hw9.ex1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StatsCollectorTest {

    public static Stream<Arguments> statsSource() {
        // Закомментированные строки - случаи когда например -6.6 != -6.600000000000000000007
        // Как такое лучше тестировать?
        return Stream.of(
            Arguments.of(
                Map.of(
                    // "name1", new double[]{-3.5, 2.1, -0.8, 1.7, -9.3, 3.2},
                    "name2", new double[]{6.4, -7.2, 4.3, 2.1, 0.9},
                    "name3", new double[]{-1.1, -2.2, -3.3, -4.4},
                    // "name4", new double[]{9.8, -5.6, 3.4, -2.3, 7.6, -1.2, -8.9},
                    "name5", new double[]{0.5, 1.2, -0.7, -2.1, 3.8, -4.5, 5.0, 6.3}
                ),
                List.of(
                    // new Metric("name1", -6.6, -1.1, -9.3, 3.2),
                    new Metric("name2", 6.5, 1.3, -7.2, 6.4),
                    new Metric("name3", -11.0, -2.75, -4.4, -1.1),
                    // new Metric("name4",  2.8, 0.4, -8.9, 9.8),
                    new Metric("name5", 9.5, 1.1875, -4.5, 6.3)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("statsSource")
    @DisplayName("StatsCollector basic test")
    void basicStatsCollectorTest(Map<String, double[]> values, List<Metric> metricsExpected) {
        StatsCollector statsCollector = new StatsCollector();
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            for (var entry : values.entrySet()) {
                executorService.execute(() -> statsCollector.push(entry.getKey(), entry.getValue()));
            }
            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertThat(statsCollector.stats()).containsExactlyInAnyOrder(metricsExpected.toArray(new Metric[]{}));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("push nullAndEmptyTest")
    void push_shouldThrowIllegalArgumentException_whenNullOrEmptyValues(double... values) {
        StatsCollector statsCollector = new StatsCollector();
        assertThatIllegalArgumentException().isThrownBy(() -> statsCollector.push("name", values));
    }
}
