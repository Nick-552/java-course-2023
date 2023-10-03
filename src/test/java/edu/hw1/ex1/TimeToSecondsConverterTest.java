package edu.hw1.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeToSecondsConverterTest {

    @Test
    @DisplayName("One minute")
    void convert_shouldReturnSeconds_whenJustOneMinute() {
        assertEquals(60, TimeToSecondsConverter.convert("01:00"));
    }

    @Test
    @DisplayName("Many minutes")
    void convert_shouldReturnSeconds_whenManyMinutes() {
        assertEquals(59975, TimeToSecondsConverter.convert("999:35"));
    }

    @Test
    @DisplayName("Too many seconds")
    void convert_shouldReturnNegativeOne_whenTooManySeconds() {
        assertEquals(-1, TimeToSecondsConverter.convert("01:60"));
    }

    @Test
    @DisplayName("Zero digits")
    void convert_shouldReturnNegativeOne_whenZeroDigits() {
        assertEquals(-1, TimeToSecondsConverter.convert(":00"));
    }

    @Test
    @DisplayName("Some letters")
    void convert_shouldReturnNegativeOne_whenNotDigits() {
        assertEquals(-1, TimeToSecondsConverter.convert("5f:a1"));
    }

    @Test
    @DisplayName("Wrong Separator")
    void convert_shouldReturnNegativeOne_whenWrongSeparator() {
        assertEquals(-1, TimeToSecondsConverter.convert("50.31"));
    }
}
