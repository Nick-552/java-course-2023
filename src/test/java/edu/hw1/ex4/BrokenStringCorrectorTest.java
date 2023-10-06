package edu.hw1.ex4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrokenStringCorrectorTest {

    @Test
    @DisplayName("EvenNumberOfChars")
    void fixString_whenEvenNumberOfChars() {
        assertEquals("214365", BrokenStringCorrector.fixString("123456"));
    }

    @Test
    @DisplayName("OddNumberOfChars")
    void fixString_whenOddNumberOfChars() {
        assertEquals("abcde", BrokenStringCorrector.fixString("badce"));
    }

    @Test
    @DisplayName("ZeroChars")
    void fixString_whenZeroChars() {
        assertEquals("", BrokenStringCorrector.fixString(""));
    }

    @Test
    @DisplayName("OneChar")
    void fixString_whenOneChar() {
        assertEquals("2", BrokenStringCorrector.fixString("2"));
    }
}
