package edu.project4.transformation;

import edu.project4.model.Point;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import static java.lang.Math.atan;

public interface Transformation extends Function<Point, Point> {

    default double omega() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return 0;
        } else {
            return Math.PI;
        }
    }

    default double rSquare(Point point) {
        return point.x() * point.x() + point.y() * point.y();
    }

    default double theta(Point point) {
        return atan(point.x() / point.y());
    }

    default double phi(Point point) {
        return atan(point.y() / point.x());
    }
}
