package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SwirlTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double rSquare = rSquare(point);
        return new Point(
            point.x() * sin(rSquare) - point.y() * cos(rSquare),
            point.x() * cos(rSquare) + point.y() * sin(rSquare)
        );
    }
}
