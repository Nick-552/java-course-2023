package edu.hw10.ex2;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CacheProxyTest {

    @Test
    void basicTest() {
        FibCalculator fib = new FibCalculatorImpl(); // persist = true
        FibCalculator proxy = CacheProxy.create(fib, FibCalculator.class);
        assertThat(proxy.fib(5)).isEqualTo(5);
        assertThat(proxy.fib(5)).isEqualTo(5);
        Sum sum = new SumImpl();
        Sum proxy1 = CacheProxy.create(sum, Sum.class);
        assertThat(proxy1.sum(2.123, -1.877)) // persist = false
            .isCloseTo(0.246, Offset.offset(0.001));
        assertThat(proxy1.sum(2.123, -1.877)) // persist = false
            .isCloseTo(0.246, Offset.offset(0.001));
        assertThat(proxy1.intSum(7, -3)).isEqualTo(4); // no cache
    }

}
