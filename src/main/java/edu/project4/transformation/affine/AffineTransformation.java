package edu.project4.transformation.affine;

import edu.project4.model.AffineTransformationCoefficients;
import edu.project4.model.Color;
import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public record AffineTransformation(AffineTransformationCoefficients coefficients, Color color)
    implements Transformation {

    /**
     * Returns: new Point(newX, newY)
     *  / a  b \    / x \    / c \    / newX \
     * |       | * |    | + |    | = |       |
     * \ d  e /    \ y /    \ f /    \ newY /
     */
    @Override
    public Point apply(Point point) {
        return new Point(
            coefficients.a() * point.x() + coefficients.b() * point.y() + coefficients.c(),
            coefficients.d() * point.x() + coefficients.e() * point.y() + coefficients.f()
        );
    }
}
