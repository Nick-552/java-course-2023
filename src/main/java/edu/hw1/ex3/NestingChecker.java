package edu.hw1.ex3;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class NestingChecker {
    private NestingChecker() {}

    public static boolean checkIfNested(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) { //Пустой массив можно вложить в любой
            return true;
        }
        if (arr2 == null || arr2.length == 0) { //Не пустой массив невозможно вложить в пустой
            return false;
        }
        int max1 = arr1[0];
        int min1 = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            max1 = max(max1, arr1[i]);
            min1 = min(min1, arr1[i]);
        }
        int max2 = arr2[0];
        int min2 = arr2[0];
        for (int i = 1; i < arr2.length; i++) {
            max2 = max(max2, arr2[i]);
            min2 = min(min2, arr2[i]);
        }
        return max1 < max2 && min1 > min2;
    }
}
