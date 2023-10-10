package edu.hw1.ex2;

public final class DigitsCounter {

    private static final int BASIS_OF_SYSTEM = 10;

    private DigitsCounter() {}

    public static int countDigits(int number) {
        int tmp = number;
        int counter = 0;
        do {
            counter++;
            tmp = tmp / BASIS_OF_SYSTEM;
        } while (tmp != 0);
        return counter;
    }
}
