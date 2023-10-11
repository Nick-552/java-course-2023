package edu.hw2.ex1;

public interface Expr {
    double evaluate();

    record Constant(double n) implements Expr {
        @Override
        public double evaluate() {
            return n;
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }

    }

    record Addition(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2.evaluate();
        }
    }

    record Multiplication(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2.evaluate();
        }
    }

    record Exponent(Expr expr, int exp) implements Expr {
        public Exponent {
            if (expr.evaluate() == 0 && exp < 0) {
                throw new ArithmeticException("Деление на 0");
            }
        }

        @Override
        public double evaluate() {
            double value = expr.evaluate();
            if (value == 1 || value == 0 && exp != 0) {
                return value;
            }
            int tmp = exp;
            double exponent = 1;
            while (tmp > 0) {
                exponent *= value;
                tmp--;
            }
            while (tmp < 0) {
                exponent /= value;
                tmp++;
            }
            return exponent;
        }
    }
}
