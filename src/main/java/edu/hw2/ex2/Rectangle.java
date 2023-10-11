package edu.hw2.ex2;

public class Rectangle {
    private int width;
    private int height;

    public final void setWidth(int width) {
        this.width = width;
    }

    public final void setHeight(int height) {
        this.height = height;
    }

    public final double area() {
        return width * height;
    }
}
