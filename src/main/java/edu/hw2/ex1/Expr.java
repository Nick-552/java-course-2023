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

    record Exponent(Expr expr, double pow) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(expr.evaluate(), pow);
        }
    }
}
