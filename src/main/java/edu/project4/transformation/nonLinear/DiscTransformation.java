package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class DiscTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double c = theta(point) / PI;
        double r = sqrt(rSquare(point));
        return new Point(
            c * sin(PI * r),
            c * cos(PI * r)
        );
    }
}
