package edu.hw2.ex2;

public class Square extends Rectangle {

    public Square(int side) {
        super(side, side);
    }

    public Square() {
    }

    public Square setSide(int side) {
        return new Square(side);
    }
}
