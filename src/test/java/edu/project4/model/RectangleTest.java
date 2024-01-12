package edu.project4.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangleTest {

    @Test
    void createRandomPoint() {
        Rectangle rectangle = Rectangle.getDefaultRectangle();
        Point point = rectangle.createRandomPoint();
        assertThat(rectangle.contains(point)).isTrue();
    }
}
