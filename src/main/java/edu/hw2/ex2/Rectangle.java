package edu.hw2.ex2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {

    }

    public final Rectangle setWidth(int width) {
        // this.width = width;
        return new Rectangle(width, this.height);
    }


    public final Rectangle setHeight(int height) {
        // this.height = height;
        return new Rectangle(this.width, height);
    }

    public final double area() {
        return width * height;
    }
}
