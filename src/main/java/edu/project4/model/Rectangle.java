package edu.project4.model;

import java.util.concurrent.ThreadLocalRandom;

public record Rectangle(double x, double y, double width, double height) {

    private static final double DEFAULT_X = -2. * 16 / 9;

    private static final double DEFAULT_Y = -2;

    private static final double DEFAULT_WIDTH = 4. * 16 / 9;

    private static final double DEFAULT_HEIGHT = 4;

    public boolean contains(Point point) {
        return point.x() >= x
            && point.x() < x + width
            && point.y() >= y
            && point.y() < y + height;
    }

    public Point createRandomPoint() {
        double px = ThreadLocalRandom.current().nextDouble(x, x + width);
        double py = ThreadLocalRandom.current().nextDouble(y, y + height);
        return new Point(px, py);
    }

    public static Rectangle getDefaultRectangle() {
        return new Rectangle(
            DEFAULT_X,
            DEFAULT_Y,
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT
        );
    }
}
