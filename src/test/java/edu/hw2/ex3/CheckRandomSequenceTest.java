package edu.hw2.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Random;
import static edu.hw2.ex3.PopularCommandExecutorTest.FIRST_IS_NOT_ZERO_SEED;
import static edu.hw2.ex3.PopularCommandExecutorTest.FIRST_IS_ZERO_SEED;
import static edu.hw2.ex3.PopularCommandExecutorTest.FIRST_THREE_ARE_LOWER_THEN_FOUR_SEED;
import static edu.hw2.ex3.PopularCommandExecutorTest.FIRST_THREE_ARE_NOT_LOWER_THEN_ZERO_SEED;
import static org.junit.jupiter.api.Assertions.*;

class CheckRandomSequenceTest {
    // checking seeds
    @Test
    @DisplayName("First zero")
    void firstIsZero_shouldReturnTrue_whenValidFirstZeroSeed() {
        assertTrue(CheckRandomSequence.firstIsZero(new Random(FIRST_IS_ZERO_SEED), 10));
        int[] sequence = CheckRandomSequence.getRandomSequence(new Random(2), 0, 10, 10);
        System.out.println(Arrays.toString(sequence));
    }

    @Test
    @DisplayName("First not zero")
    void firstIsZero_shouldReturnFalse_whenValidFirstNotZeroSeed() {
        assertFalse(CheckRandomSequence.firstIsZero(new Random(FIRST_IS_NOT_ZERO_SEED), 10));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0, 10, 3"
    })
    @DisplayName("Some first numbers lower then 4")
    void getRandomSequence_shouldReturnSequenceWithNumbersLowerThenFour_whenValidSeed(int origin, int bound, int amount) {
        int[] sequence = CheckRandomSequence.getRandomSequence(
            new Random(FIRST_THREE_ARE_LOWER_THEN_FOUR_SEED), origin, bound, amount
        );
        for (int i = 0; i < amount; i++) {
            assertTrue(sequence[i] < 4);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0, 10, 3"
    })
    @DisplayName("Some of first numbers not lower then 4")
    void getRandomSequence_shouldReturnSequenceWithNumberNotLowerThenFour_whenValidSeed(int origin, int bound, int amount) {
        int[] sequence = CheckRandomSequence.getRandomSequence(
            new Random(FIRST_THREE_ARE_NOT_LOWER_THEN_ZERO_SEED), origin, bound, amount
        );
        boolean found = false;
        for (int i = 0; i < amount; i++) {
            if (sequence[i] >= 4) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
