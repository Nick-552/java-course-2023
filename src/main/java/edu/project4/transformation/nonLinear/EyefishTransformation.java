package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.sqrt;

public class EyefishTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double coef = 2 / (sqrt(rSquare(point)) + 1);
        return new Point(
            coef * point.x(),
            coef * point.y()
        );
    }
}
