package edu.hw8.ex2;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FibonacciUtils {

    // Я не понял как мне распараллелить вычисления числа Фибоначчи.
    // Если имееется в виду вычисление нескольких чисел, то это эквивалентно вычислению наибольшего:
    // если наибольшее вычислено, то и все до него тоже (можно сохранять их в List, например).
    // При этом на каждой итерации вычисления мы используем результат предыдущих итераций

    public static long compute(int num) {
        long a = 0;
        long b = 1;
        for (int i = 0; i < num; i++) {
            long tmp = a + b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
