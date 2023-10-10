package edu.hw1.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TimeToSecondsConverterTest {
    @ParameterizedTest
    @CsvSource(value = {"01:00, 60", "999:35, 59975"})
    @DisplayName("Basic tests")
    void convert_shouldReturnCorrect_whenCorrectFormat(String time, int expected) {
        assertEquals(expected, TimeToSecondsConverter.convert(time));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01:60", ":00", "5f:a1", "50.31"})
    @DisplayName("Wrong format")
    void convert_shouldReturnMinusOne_whenWrongInput(String input) {
        assertEquals(-1, TimeToSecondsConverter.convert(input));
    }
}
