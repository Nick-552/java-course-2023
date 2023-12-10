package edu.project4.model;

public record Point(double x, double y) {

    public Point rotatedBy(double phi) {
        double cosPhi = Math.cos(phi);
        double sinPhi = Math.sin(phi);
        return new Point(
            x * cosPhi - y * sinPhi,
            x * sinPhi + y * cosPhi
        );
    }
}
