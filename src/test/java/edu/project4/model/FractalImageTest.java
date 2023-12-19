package edu.project4.model;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class FractalImageTest {

    public static Stream<Arguments> containsSource() {
        return Stream.of(
            Arguments.of(9, 4, true),
            Arguments.of(0, 0, true),
            Arguments.of(-1, -1, false),
            Arguments.of(10, 4, false)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0, 0",
        "10, 10",
        "34, 43",
        "0, 43",
        "21, 0"
    })
    void create(int width, int height) {
        FractalImage image = FractalImage.create(width, height);
        Pixel[][] data = new Pixel[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[j][i] = new Pixel(i, j, Color.getEmpty());
            }
        }
        FractalImage expected = new FractalImage(data, width, height);
        assertThat(image.width()).isEqualTo(expected.width());
        assertThat(image.height()).isEqualTo(expected.height());
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertThat(image.data()[i][j]).isEqualTo(expected.data()[i][j]);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("containsSource")
    void contains(int x, int y, boolean expected) {
        FractalImage image = FractalImage.create(10, 5);
        assertThat(image.contains(x, y)).isEqualTo(expected);
    }
}
