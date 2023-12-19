package edu.hw10.ex2;

public class SumImpl implements Sum {

    @Override
    public double sum(double a, double b) {
        return Double.sum(a, b);
    }

    @Override
    public int intSum(int a, int b) {
        return a + b;
    }
}
