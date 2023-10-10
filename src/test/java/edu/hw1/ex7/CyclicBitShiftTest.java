package edu.hw1.ex7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CyclicBitShiftTest {
    @ParameterizedTest
    @CsvSource(value = {
        "8, 1, 1",
        "14, 1, 13",
        "17, 2, 6",
        "16, 1, 1",
        "5, 0, 5", // shift = 0
        "16, 0, 16",
        "5, 5, 6", // shift >= full cycle
        "16, 5, 16"
    })
    @DisplayName("Basic rotate left")
    void rotateLeft_basicTest(int n, int shift, int expected) {
        assertEquals(expected, CyclicBitShift.rotateLeft(n, shift));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "8, 1, 4",
        "14, 1, 7",
        "17, 2, 12",
        "3, 1, 3",
        "5, 0, 5", // shift = 0
        "16, 0, 16",
        "5, 5, 3", // shift >= full cycle
        "16, 5, 16"
    })
    @DisplayName("Basic rotate right")
    void rotateRight_basicTest(int n, int shift, int expected) {
        assertEquals(expected, CyclicBitShift.rotateRight(n, shift));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0, 10",
        "-5, 10",
    })
    @DisplayName("n <= 0")
    void rotateLeftAndRotateRight_shouldReturnMinusOne_whenNumIsLowerOrZero(int n, int shift) {
        assertEquals(-1, CyclicBitShift.rotateLeft(n, shift));
        assertEquals(-1, CyclicBitShift.rotateRight(n, shift));
    }
}
