package edu.hw1.ex2;

public final class DigitsCounter {

    private static final int TEN = 10;

    private DigitsCounter() {}

    public static int countDigits(int number) {
        int tmp = number;
        int ctr = 0;
        do {
            ctr++;
            tmp = tmp / TEN;
        } while (tmp != 0); // OMG DO WHILE CYCLE!!! (just used to not handle 0 case in a separate if)
        return ctr;
    }
}
