package edu.hw11.ex3;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciUtilsTest {
    private static final Class<?> fibonacciUtils = ByteBuddyClassCreator.createClassWithFibMethod();

    private static final Method fib;

    static {
        try {
            fib = fibonacciUtils.getDeclaredMethod("fib", int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource(value = {
        "-5, -1",
        " 0,  0",
        " 1,  1",
        " 6,  8"
    })
    void fib_shouldReturnFibonacciNumber(int n, long expected) {
        assertThat((long) fib.invoke(null, n))
            .isEqualTo(expected);
    }
}
