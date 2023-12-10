package edu.project4.utils;

import edu.project4.format.ImageFormat;
import edu.project4.model.FractalImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageUtils {

    @SneakyThrows
    public static void saveFractalFlame(FractalImage fractalImage, Path path, ImageFormat format) {
        BufferedImage image = new BufferedImage(
            fractalImage.width(),
            fractalImage.height(),
            BufferedImage.TYPE_INT_RGB
        );
        File imageFile = path.toFile();
        if (!imageFile.isFile()) {
            if (imageFile.isDirectory()) {
                imageFile = Files.createFile(
                    path.resolve("image" + UUID.randomUUID() + "." + format.name().toLowerCase())
                ).toFile();
            } else {
                imageFile = Files.createFile(path).toFile();
            }
        }
        for (int y = 0; y < fractalImage.height(); y++) {
            for (int x = 0; x < fractalImage.width(); x++) {
                var color = fractalImage.data()[y][x].color();
                int rgb = (color.getR() << 16) | (color.getG() << 8) | (color.getB());
                image.setRGB(x, y, rgb);
            }
        }
        ImageIO.write(image, format.name().toLowerCase(), imageFile);
    }
}
