package edu.project2.generator;

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
    void initMaze_shouldThrowIllegalArgumentException_whenWrongSize(int height, int width) {
        assertThatIllegalArgumentException().isThrownBy(() -> new MazeRecursiveBacktrackerGenerator().initMaze(height, width));
        assertThatIllegalArgumentException().isThrownBy(() -> new MazeOldosBroderGenerator().initMaze(height, width));

    }
}
