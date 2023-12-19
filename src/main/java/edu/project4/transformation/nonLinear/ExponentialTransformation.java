package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;

public class ExponentialTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double exp = exp(point.x() - 1);
        return new Point(
            exp * cos(PI * point.y()),
            exp * sin(PI * point.y())
        );
    }
}
