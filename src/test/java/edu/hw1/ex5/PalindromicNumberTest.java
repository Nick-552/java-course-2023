package edu.hw1.ex5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PalindromicNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-345, -11211230, -13001120, -23336014, -11})
    @DisplayName("Descendant Negative")
    void isPalindromeDescendant_shouldReturnAsIfItsPositive_whenNegative(int input) {
        assertEquals(
            PalindromicNumber.isPalindromeDescendant(-input),
            PalindromicNumber.isPalindromeDescendant(input)
        );
    }
    @DisplayName("Descendant positive true")
    @ParameterizedTest(name = "{index} - {0} - Descendant Positive true")
    @ValueSource(ints = {11211230, 13001120, 23336014, 11})
    void isPalindromeDescendant_whenPositive1(int testInt) {
        assertTrue(PalindromicNumber.isPalindromeDescendant(testInt));
    }

    @Test
    @DisplayName("makeNew for 11211230")
    void makeNew1() {
        assertEquals(2333, PalindromicNumber.makeNew(11211230));
    }

    @Test
    @DisplayName("MakeNew for 2333")
    void makeNew2() {
        assertEquals(56, PalindromicNumber.makeNew(2333));
    }

    @Test
    @DisplayName("MakeNew for 56")
    void makeNew3() {
        assertEquals(11, PalindromicNumber.makeNew(56));
    }

    @Test
    @DisplayName("isPalindrome1")
    void isPalindrome_shouldReturnFalse_whenNotPalindrome() {
        assertFalse(PalindromicNumber.isPalindrome(345));
    }
    @Test
    @DisplayName("isPalindrome2")
    void isPalindrome_shouldReturnTrue_whenPalindrome1() {
        assertTrue(PalindromicNumber.isPalindrome(343));
    }

    @Test
    @DisplayName("isPalindrome3")
    void isPalindrome_shouldReturnTrue_whenPalindrome2() {
        assertTrue(PalindromicNumber.isPalindrome(11));
    }
}
