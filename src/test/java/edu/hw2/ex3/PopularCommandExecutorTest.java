package edu.hw2.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopularCommandExecutorTest {
    // SEEDS
    private static final long FIRST_IS_ZERO_SEED = 0;
    // new Random(0).nextInt(0, 10) generates 0
    private static final long FIRST_IS_NOT_ZERO_SEED = 1;
    // new Random(1).nextInt(0, 10) generates not 0
    private static final long FIRST_THREE_ARE_LOWER_THEN_FOUR_SEED = 4;
    // new Random(4).nextInt(0, 10) generates {2, 2, 3, ...}
    private static final long FIRST_THREE_ARE_NOT_LOWER_THEN_ZERO_SEED = 2;
    // new Random(2).nextInt(0, 10) generates {8, 2, 0, ...}


    // PopularCommandExecutors with seeded randoms
    private static final PopularCommandExecutor failingExecute =
        new PopularCommandExecutor(
            3, FIRST_IS_ZERO_SEED, FIRST_THREE_ARE_LOWER_THEN_FOUR_SEED
        );

    private static final PopularCommandExecutor stableConnection =
        new PopularCommandExecutor(
            3, FIRST_IS_NOT_ZERO_SEED, FIRST_THREE_ARE_LOWER_THEN_FOUR_SEED
        );

    private static final PopularCommandExecutor faultyConnectionButSomeAttemptsAreSuccessful =
        new PopularCommandExecutor(
            3, FIRST_IS_ZERO_SEED, FIRST_THREE_ARE_NOT_LOWER_THEN_ZERO_SEED
        );

    // Tests
    @Test
    @DisplayName("Execution failed (faulty connection, all attempts failed)")
    void updatePackages_shouldThrowConnectionException_whenFaultyConnectionAndAllAttemptsFailed() {
        assertThrows(ConnectionException.class, failingExecute::updatePackages);
    }

    @Test
    @DisplayName("Executed successfully (stable connection)")
    void updatePackages_shouldNotThrowConnectionException_whenStableConnection() {
        assertDoesNotThrow(stableConnection::updatePackages);
    }

    @Test
    @DisplayName("Executed successfully (faulty connection, but has successful attempt)")
    void updatePackages_shouldNotThrowConnectionException_whenFaultyConnectionSuccessfulAttempts() {
        assertDoesNotThrow(faultyConnectionButSomeAttemptsAreSuccessful::updatePackages);
    }
}
