package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class SpiralTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = sqrt(rSquare(point));
        double theta = theta(point);
        return new Point(
            1 / r * (cos(theta) + sin(r)),
            1 / r * (sin(theta) - cos(r))
        );
    }
}
