package edu.hw1.ex2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitsCounterTest {

    @Test
    @DisplayName("0")
    void count_whenZero() {
        assertEquals(1, DigitsCounter.countDigits(0));
    }

    @Test
    @DisplayName("Negative")
    void count_whenNegative() {
        assertEquals(3, DigitsCounter.countDigits(-123));
    }

    @Test
    @DisplayName("Positive")
    void count_whenPositive() {
        assertEquals(3, DigitsCounter.countDigits(123));
    }
}
