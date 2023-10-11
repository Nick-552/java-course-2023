package edu.hw2.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ExprTest {
    @Test
    @DisplayName("Tinkoff")
    void expr_shouldWorkCorrectly_whenBasicOperations() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        assertEquals(37, res.evaluate());
    }

    @ParameterizedTest
    @CsvSource(value = { // exprConstant, exponent, expected
        "-2, -3, -0.125",
        "2, 6, 64",
        "5, 0, 1",
        "0, 0, 1",
        "0, 150, 0",
        "0.25, -2, 16",
        "1, 100, 1",
        "-1, 100, 1",
        "-1, 99, -1",
        "-1, -99, -1",
        "-1, -100, 1"
    })
    @DisplayName("Evaluate Exponent")
    void evaluateExponent_shouldWorkCorrectly_whenBasicTests(double exprConstant, int exponent, double expected) {
        var exp = new Expr.Exponent(new Expr.Constant(exprConstant), exponent);
        assertEquals(expected, exp.evaluate());
    }

    @Test
    @DisplayName("Wrong Exponent Expr")
    void Exponent_shouldThrowArithmeticException_whenExprIsZeroAndExpLowerThenZero() {
        assertThrows(ArithmeticException.class, () -> new Expr.Exponent(new Expr.Constant(0), -5));
    }
}
