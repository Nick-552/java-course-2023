package edu.project4.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AffineTransformationCoefficientsTest {

    @Test
    void create_shouldReturnValidCoefs() {
        for (int i = 0; i < 100; i++) {
            AffineTransformationCoefficients coeffs = AffineTransformationCoefficients.create();
            assertThat(areCoefsValid(coeffs)).isTrue();
        }
    }

    private static boolean areCoefsValid(AffineTransformationCoefficients coefs) {
        return coefs.a()* coefs.a()+ coefs.d()* coefs.d()< 1
            && coefs.b() * coefs.b() + coefs.e()* coefs.e()< 1
            && (coefs.a()* coefs.a()+ coefs.b() * coefs.b() + coefs.d()* coefs.d()+ coefs.e() * coefs.e()) < 1 + (coefs.a()* coefs.e()- coefs.b() * coefs.d()) * (coefs.a()* coefs.e()- coefs.b() * coefs.d());
    }
}
