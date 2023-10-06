package edu.hw1.ex6;

public final class ConstantOfKaprekar {
    public static final int CONSTANT_OF_KAPREKAR = 6174;
    private static final int THOUSAND = 1000;
    private static final int TEN = 10;

    private ConstantOfKaprekar() {
    }

    public static int countKaprekar(int number) {
        if (number <= THOUSAND || number >= THOUSAND * TEN || makeNext(number) == 0) {
            return -1;
        } else {
            return countK(number);
        }
    }

    private static int countK(int number) {
        if (number == CONSTANT_OF_KAPREKAR) {
            return 0;
        }
        return 1 + countK(makeNext(number));
    }

    protected static int makeNext(int number) {
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
        if (maxN - minN >= THOUSAND) {
            return maxN - minN;
        } else {
            return TEN * (maxN - minN);
        }
    }
}
