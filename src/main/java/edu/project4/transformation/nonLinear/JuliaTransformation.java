package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class JuliaTransformation implements Transformation {

    private final double omega = omega();

    @Override
    public Point apply(Point point) {
        double sqrtR = sqrt(sqrt(rSquare(point)));
        double tmp = theta(point) / 2 + omega;
        return new Point(
            sqrtR * cos(tmp),
            sqrtR * sin(tmp)
        );
    }
}
