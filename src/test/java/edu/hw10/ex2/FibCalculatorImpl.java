package edu.hw10.ex2;

public class FibCalculatorImpl implements FibCalculator {

    @Override
    public long fib(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be greater or equal to 0");
        } else if (number < 2) {
            return number;
        } else {
            return fib(number - 1) + fib(number - 2);
        }
    }
}
