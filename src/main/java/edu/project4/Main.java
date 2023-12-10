package edu.project4;

import edu.project4.format.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.nio.file.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            ImageUtils.saveFractalFlame(
                FractalFlameGenerator.generateRandom(false),
                Path.of(
                    "C:\\Users\\Nick\\Desktop\\Uchoba\\TinkoffGit\\фрактальное пламя\\randomGeneration\\no symmetry"
                ),
                ImageFormat.PNG
            );
            System.out.println(i);
        }
    }
}
