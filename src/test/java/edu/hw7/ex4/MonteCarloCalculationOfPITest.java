package edu.hw7.ex4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;
import static org.assertj.core.api.Assertions.assertThat;

class MonteCarloCalculationOfPITest {

    @Test
    @DisplayName("timeAndAccuracy test")
    void timeAndAccuracy() {
        long startTime = System.nanoTime();
        double pi1 = MonteCarloCalculationOfPI.calculatePiParallel(1_000_000_000L);
        long endTime = System.nanoTime();
        long time1 = endTime - startTime;
        startTime = System.nanoTime();
        double pi2 = MonteCarloCalculationOfPI.calculatePiNotParallel(1_000_000_000);
        endTime = System.nanoTime();
        long time2 = endTime - startTime;
        assertThat((float) time2 / time1).isGreaterThan(5);
        assertThat(abs(pi1 - Math.PI)).isLessThan(0.005);
        assertThat(abs(pi2 - Math.PI)).isLessThan(0.005);
    }
}
