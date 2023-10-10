package edu.hw1.ex6;

public final class ConstantOfKaprekar {
    public static final int CONSTANT_OF_KAPREKAR = 6174;
    private static final int MIN_NUMBER = 1000;
    private static final int BASIS_OF_SYSTEM = 10;

    private ConstantOfKaprekar() {
    }

    public static int countKaprekar(int number) {
        if (number <= MIN_NUMBER || number >= MIN_NUMBER * BASIS_OF_SYSTEM || makeNext(number) == 0) {
            return -1;
        } else {
            return countKaprekarRecursion(number);
        }
    }

    static int countKaprekarRecursion(int number) {
        if (number == CONSTANT_OF_KAPREKAR) {
            return 0;
        }
        return 1 + countKaprekarRecursion(makeNext(number));
    }

    static int makeNext(int number) {
        StringBuilder sortedNum = new StringBuilder(
            String.valueOf(number)
                .chars().sorted()
                .collect(
                    StringBuilder::new,
                    StringBuilder::appendCodePoint,
                    StringBuilder::append
                )
        );
        int minN = Integer.parseInt(sortedNum.toString());
        int maxN = Integer.parseInt(sortedNum.reverse().toString());
        int newN = maxN - minN;
        if (newN >= MIN_NUMBER) {
            return newN;
        } else {
            return BASIS_OF_SYSTEM * (newN);
        }
    }
}
