package edu.hw10.ex2;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
