package edu.hw10.ex2;

public interface Sum {

    @Cache
    double sum(double a, double b);

    int intSum(int a, int b);
}
