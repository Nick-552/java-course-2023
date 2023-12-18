package edu.project4.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    public static Stream<Arguments> sourceForMixingColors() {
        return Stream.of(
            Arguments.of(Color.getEmpty(), new Color(12, 75, 109), new Color(12, 75, 109)),
            Arguments.of(new Color(12, 75, 109), new Color(66, 13, 54), new Color(39, 44, 81))
        );
    }

    @Test
    void createRandomColor() {
        Color c1 = Color.createRandomColor();
        Color c2 = Color.createRandomColor();
        assertThat(c1.getHitCount()).isEqualTo(1);
        assertThat(c1).isNotEqualTo(c2);
    }

    @Test
    void getEmpty() {
        Color empty = Color.getEmpty();
        assertThat(empty.getHitCount()).isEqualTo(0);
        assertThat(empty.getR()).isEqualTo(0);
        assertThat(empty.getG()).isEqualTo(0);
        assertThat(empty.getB()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("sourceForMixingColors")
    void mix(Color c1, Color c2, Color expectedOnlyColor) {
        int hits = c1.getHitCount() + c2.getHitCount();
        c1.mix(c2);
        assertThat(c1.getR()).isEqualTo(expectedOnlyColor.getR());
        assertThat(c1.getG()).isEqualTo(expectedOnlyColor.getG());
        assertThat(c1.getB()).isEqualTo(expectedOnlyColor.getB());
        assertThat(c1.getHitCount()).isEqualTo(hits);
    }
}
