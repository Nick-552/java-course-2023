package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class PowerTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double theta = theta(point);
        double coef = pow(sqrt(rSquare(point)), sin(theta));
        return new Point(
            coef * cos(theta),
            coef * sin(theta)
        );
    }
}
