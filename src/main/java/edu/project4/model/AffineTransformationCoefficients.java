package edu.project4.model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record AffineTransformationCoefficients(double a, double b, double c, double d, double e, double f) {

    public static AffineTransformationCoefficients create() {
        Random random = ThreadLocalRandom.current();
        while (true) {
            double a = random.nextDouble(-1, 1);
            double b = random.nextDouble(-1, 1);
            double c = random.nextDouble(-1, 1);
            double d = random.nextDouble(-1, 1);
            double e = random.nextDouble(-1, 1);
            double f = random.nextDouble(-1, 1);
            if (areCoefsValid(a, b, c, d, e, f)) {
                return new AffineTransformationCoefficients(a, b, c, d, e, f);
            }
        }
    }

    private static boolean areCoefsValid(double a, double b, double c, double d, double e, double f) {
        return a * a + d * d < 1
            && b * b + e * e < 1
            && (a * a + b * b + d * d + e * e) < 1 + (a * e - b * d) * (a * e - b * d);
    }
}
