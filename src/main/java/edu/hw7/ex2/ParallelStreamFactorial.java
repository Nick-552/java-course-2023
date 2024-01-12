package edu.hw7.ex2;

import java.math.BigInteger;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParallelStreamFactorial {

    public static BigInteger factorial(int n) {
        return IntStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
