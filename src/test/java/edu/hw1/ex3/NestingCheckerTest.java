package edu.hw1.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NestingCheckerTest {

    @Test
    @DisplayName("First empty")
    void checkIfNested_shouldReturnTrue_whenFirstArrayIsEmpty() {
        assertTrue(NestingChecker.checkIfNested(new int[0], null));
    }

    @Test
    @DisplayName("First not empty, but second is")
    void checkIfNested_shouldReturnFalse_whenSecondArrayIsEmptyButNotFirst() {
        assertFalse(NestingChecker.checkIfNested(new int[1], new int[0]));
    }

    @Test
    @DisplayName("Not empty and nested")
    void checkIfNested_shouldReturnTrue_whenNotEmptyAndNested() {
        assertTrue(NestingChecker.checkIfNested(new int[]{1, 2, 3, 4}, new int[]{0,6}));
    }

    @Test
    @DisplayName("Not empty and not nested")
    void checkIfNested_shouldReturnFalse_whenNotEmptyAndNotNested() {
        assertFalse(NestingChecker.checkIfNested(new int[]{9, 9, 8}, new int[]{8,9}));
    }
}
