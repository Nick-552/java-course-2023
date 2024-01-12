package edu.hw11.ex2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RedefinedMethodTest {

    static {
        MethodRedefiningUtils.redefineArithmeticUtilsSumMethod();
    }

    @Test
    void sum_shouldReturnProduct() {
        assertThat(ArithmeticUtils.sum(3, 4)).isEqualTo(12);
    }
}
