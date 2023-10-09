package edu.hw1.ex3;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class NestingChecker {
    private NestingChecker() {}

    public static boolean checkIfNested(int[] internal, int[] external) {
        if (internal == null || internal.length == 0) { //Пустой массив можно вложить в любой
            return true;
        }
        if (external == null || external.length == 0) { //Не пустой массив невозможно вложить в пустой
            return false;
        }
        int max1 = internal[0];
        int min1 = internal[0];
        for (int i = 1; i < internal.length; i++) {
            max1 = max(max1, internal[i]);
            min1 = min(min1, internal[i]);
        }
        int max2 = external[0];
        int min2 = external[0];
        for (int i = 1; i < external.length; i++) {
            max2 = max(max2, external[i]);
            min2 = min(min2, external[i]);
        }
        return max1 < max2 && min1 > min2;
    }
}
