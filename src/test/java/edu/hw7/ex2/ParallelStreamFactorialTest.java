package edu.hw7.ex2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;

class ParallelStreamFactorialTest {

    @ParameterizedTest
    @CsvSource(value = {
        "-10, 1",
        "0, 1",
        "1, 1",
        "6, 720"
    })
    void factorial(int n, BigInteger factorialExpected) {
        assertThat(ParallelStreamFactorial.factorial(n)).isEqualTo(factorialExpected);
    }
}
