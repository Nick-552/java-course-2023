package edu.project4;

import org.junit.jupiter.api.Test;

class FractalFlameGeneratorTest {

    @Test
    void generateRandom() {
        for (int i = 0; i < 10; i++) {
            FractalFlameGenerator.generateRandom(i % 2 == 0);
        }
    }
}
