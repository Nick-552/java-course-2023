package edu.hw1.ex5;

import static java.lang.Math.abs;

public final class PalindromicNumber {
    private PalindromicNumber() {}

    private static final int TEN = 10;

    public static boolean isPalindromeDescendant(int number) {
        if (number < 0) number = abs(number);
        if (number / TEN == 0) {
            return false;
        } else if (isPalindrome(number)) {
            return true;
        }
        return isPalindromeDescendant(makeNew(number));
    }

    public static int makeNew(int number) {
        int tmp = number;
        int newNumber = 0;
        int multiplier = 1;
        while (tmp > 0) {
            int d1 = tmp % TEN;
            tmp /= TEN;
            int d2 = tmp % TEN;
            tmp /= TEN;
            newNumber += (d1 + d2) * multiplier;
            if (d1 + d2 < TEN) {
                multiplier *= TEN;
            } else {
                multiplier *= TEN * TEN;
            }
        }
        return newNumber;
    }

    public static boolean isPalindrome(int number) {
        StringBuilder stringBuilder = new StringBuilder(Integer.toString(number));
        StringBuilder stringBuilderReversed = new StringBuilder(stringBuilder).reverse();
        return stringBuilder.compareTo(stringBuilderReversed) == 0;
    }
}
