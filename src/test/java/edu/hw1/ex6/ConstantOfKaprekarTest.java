package edu.hw1.ex6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ConstantOfKaprekarTest {

    @ParameterizedTest
    @CsvSource(value = {
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
    })
    @DisplayName("Basic tests")
    void countKaprekar_basicTestWithCsvSource(int input, int expected) {
        assertEquals(expected, ConstantOfKaprekar.countKaprekar(input));
    }

    @Test
    @DisplayName("difference = 999")
    void countKaprekar_shoulWork_whenInputIsSpecialNumberWithDifference999() {
        assertEquals(5, ConstantOfKaprekar.countKaprekar(1222));
    }

    @ParameterizedTest
    @ValueSource(ints = {1111, 1000, 45, 12345})
    @DisplayName("Basic tests")
    void countKaprekar_shouldReturnMinusOne_whenInputIsIncorrect(int input) {
        assertEquals(-1, ConstantOfKaprekar.countKaprekar(input));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3524, 3087",
        "1112, 9990",
        "6554, 1998",
        "1379, 8352",
    })
    @DisplayName("Basic making next tests")
    void makeNext_basicTestWithCsvSource(int input, int expected) {
        assertEquals(expected, ConstantOfKaprekar.makeNext(input));
    }
}
