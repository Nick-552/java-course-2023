package edu.project4.model;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    void rotatedBy() {
        Point actual = new Point(2, 5).rotatedBy(PI);
        Point expected = new Point(-2, -5);
        assertThat(actual.x()).isCloseTo(expected.x(), Offset.offset(0.00001));
        assertThat(actual.y()).isCloseTo(expected.y(), Offset.offset(0.00001));
    }
}
