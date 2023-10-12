package edu.hw2.ex3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class CheckRandomSequenceTest {

    @Test
    void firstIsZero() {
        assertTrue(CheckRandomSequence.firstIsZero(new Random(0), 10));
        int[] sequence = CheckRandomSequence.getRandomSequence(new Random(2), 0, 10, 10);
        System.out.println(Arrays.toString(sequence));
    }
}
