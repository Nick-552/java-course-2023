package edu.project4.transformation.nonLinear;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public class SphericalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double rSquare = rSquare(point);
        return new Point(
            point.x() / rSquare,
            point.y() / rSquare
        );
    }
}
