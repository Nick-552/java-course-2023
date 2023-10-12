package edu.hw2.ex3;

import java.util.Random;

public final class CheckRandomSequence {
    private CheckRandomSequence() {}

    public static int[] getRandomSequence(Random random, int origin, int bound, int amount) {
        int[] sequence = new int[amount];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = random.nextInt(origin, bound);
        }
        return sequence;
    }

    public static boolean firstIsZero(Random random, int bound) {
        int first = random.nextInt(0, bound);
        return first == 0;
    }
}
