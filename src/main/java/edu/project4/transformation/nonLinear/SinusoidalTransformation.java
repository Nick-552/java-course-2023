package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.sin;

public class SinusoidalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(
            sin(point.x()),
            sin(point.y())
        );
    }
}
