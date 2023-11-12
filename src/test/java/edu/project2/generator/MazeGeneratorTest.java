package edu.project2.generator;

import edu.project2.maze.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MazeGeneratorTest {

    @ParameterizedTest
    @DisplayName("wrong height & width")
    @CsvSource(value = {
        "1, 1",
        "4, 4",
        "-3, -3"
    })
    void new_shouldThrowIllegalArgumentException_whenWrongSize(int height, int width) {
        assertThatIllegalArgumentException().isThrownBy(() -> new AbstractMazeGenerator(height, width) {
            @Override
            public Maze generate() {
                return null;
            }
        });
    }
}
