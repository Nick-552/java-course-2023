package edu.project4.imageProcessor;

import edu.project4.model.FractalImage;
import lombok.AllArgsConstructor;
import static java.lang.Math.max;

@AllArgsConstructor
public class ColorCorrection implements ImageProcessor {

    private double gamma;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                if (image.data()[i][j].color().getHitCount() != 0) {
                    max = max(image.data()[i][j].color().computeNormal(), max);
                }
            }
        }
        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                image.data()[i][j].color().processGammaCorrection(max, gamma);
            }
        }
    }
}
