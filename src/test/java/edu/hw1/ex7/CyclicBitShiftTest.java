package edu.hw1.ex7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CyclicBitShiftTest {

    @ParameterizedTest
    @CsvSource(value = {
        "8, 1, 1",
        "14, 1, 13",
        "17, 2, 6",
        "16, 1, 1"
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
        "3, 1, 3"
    })
    @DisplayName("Basic rotate right")
    void rotateRight_basicTest(int n, int shift, int expected) {
        assertEquals(expected, CyclicBitShift.rotateRight(n, shift));
    }

    @Test
    @DisplayName("n <= 0")
    void rotateLeftAndRotateRight_shouldReturnMinusOne_whenNumIsLowerOrZero() {
        assertEquals(-1, CyclicBitShift.rotateLeft(0, 10));
        assertEquals(-1, CyclicBitShift.rotateLeft(-5, 10));
        assertEquals(-1, CyclicBitShift.rotateRight(0, 10));
        assertEquals(-1, CyclicBitShift.rotateRight(-5, 10));
    }

    @Test
    @DisplayName("shift = 0")
    void rotateLeftAndRotateRight_shouldReturnInput_whenShiftIsZero() {
        assertEquals(5, CyclicBitShift.rotateLeft(5, 0));
        assertEquals(16, CyclicBitShift.rotateLeft(16, 0));
        assertEquals(5, CyclicBitShift.rotateRight(5, 0));
        assertEquals(16, CyclicBitShift.rotateRight(16, 0));
    }

    @Test
    @DisplayName("shift >= bitsAmount")
    void rotateLeftAndRotateRight_whenShiftIsBitsAmountOrHigher() {
        assertEquals(6, CyclicBitShift.rotateLeft(5, 5));
        assertEquals(16, CyclicBitShift.rotateLeft(16, 5));
        assertEquals(3, CyclicBitShift.rotateRight(5, 5));
        assertEquals(16, CyclicBitShift.rotateRight(16, 5));
    }

}
