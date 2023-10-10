package edu.hw1.ex5;

import static java.lang.Math.abs;

public final class PalindromicNumber {
    private PalindromicNumber() {}

    private static final int BASIS_OF_SYSTEM = 10;

    public static boolean isPalindromeDescendant(int number) {
        int tmp = number;
        if (number < 0) {
            tmp = abs(number);
        }
        if (tmp / BASIS_OF_SYSTEM == 0) {
            return false;
        } else if (isPalindrome(tmp)) {
            return true;
        }
        return isPalindromeDescendant(makeNew(tmp));
    }

    static int makeNew(int number) {
        int tmp = number;
        int newNumber = 0;
        int multiplier = 1;
        while (tmp > 0) {
            int d1 = tmp % BASIS_OF_SYSTEM;
            tmp /= BASIS_OF_SYSTEM;
            int d2 = tmp % BASIS_OF_SYSTEM;
            tmp /= BASIS_OF_SYSTEM;
            newNumber += (d1 + d2) * multiplier;
            if (d1 + d2 < BASIS_OF_SYSTEM) {
                multiplier *= BASIS_OF_SYSTEM;
            } else {
                multiplier *= BASIS_OF_SYSTEM * BASIS_OF_SYSTEM;
            }
        }
        return newNumber;
    }

    static boolean isPalindrome(int number) {
        StringBuilder stringBuilder = new StringBuilder(Integer.toString(number));
        StringBuilder stringBuilderReversed = new StringBuilder(stringBuilder).reverse();
        return stringBuilder.compareTo(stringBuilderReversed) == 0;
    }
}
