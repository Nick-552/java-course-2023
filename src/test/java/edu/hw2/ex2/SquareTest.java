package edu.hw2.ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void setSide() {
        Square square = new Square();
        square = square.setSide(12);
        assertEquals(144.0, square.area());
    }
}
