package edu.project4.utils;

import edu.project4.format.ImageFormat;
import edu.project4.model.FractalImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class ImageUtilsTest {

    @Test
    void saveFractalFlame(@TempDir Path path) {
        FractalImage image = FractalImage.create(10, 10);
        Path created = ImageUtils.saveFractalFlame(image, path.resolve("testFractal.png"), ImageFormat.PNG);
        Path expected = path.resolve("testFractal.png");
        assertThat(created.toFile().exists()).isTrue();
        assertThat(created).isEqualTo(expected);
    }
}
